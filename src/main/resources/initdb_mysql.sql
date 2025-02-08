CREATE USER 'ecommerce_user'@'%' IDENTIFIED BY 'qwerty';
GRANT ALL PRIVILEGES ON *.* TO 'ecommerce_user'@'%' WITH GRANT OPTION;

-- Create the database
CREATE schema ecommerce_db;
USE ecommerce_db;

-- Create the 'clients' table
CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    document_number VARCHAR(20) UNIQUE NOT NULL,
    document_type ENUM('DNI', 'PASSPORT', 'RUC') NOT NULL
);

-- Create the 'products' table
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    stock INT NOT NULL CHECK (stock >= 0),
    price DECIMAL(10,2) NOT NULL CHECK (price >= 0),
    category VARCHAR(50),
    description TEXT,
    enabled BOOLEAN DEFAULT TRUE
);

-- Create the 'shopping_cart' table (one per client)
CREATE TABLE shopping_carts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT UNIQUE NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);

-- Create the 'products_shopping_cart' table (many-to-many relationship)
CREATE TABLE products_shopping_carts (
    product_id INT NOT NULL,
    shopping_cart_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    PRIMARY KEY (product_id, shopping_cart_id),
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (shopping_cart_id) REFERENCES shopping_carts(id) ON DELETE CASCADE
);

-- Create the 'invoices' table
CREATE TABLE invoices (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);

-- Create the 'products_invoices' table (many-to-many relationship with quantity)
CREATE TABLE products_invoices (
    product_id INT NOT NULL,
    invoice_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    unit_price DECIMAL(10,2) NOT NULL CHECK (unit_price >= 0),
    PRIMARY KEY (product_id, invoice_id),
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (invoice_id) REFERENCES invoices(id) ON DELETE CASCADE
);

-- Insert sample data for 'clients'
INSERT INTO clients (name, document_number, document_type) VALUES
('Elon Musk', '12345678', 'DNI'),
('Mark Zuckerberg', '87654321', 'DNI'),
('Linus Torvalds', '123456789012', 'RUC'),
('Freddy Vega', '45612378', 'DNI'),
('Sam Altman', '78965432', 'PASSPORT'),
('Bill Gates', '32165498', 'DNI');

-- Insert sample data for 'products' (Real product names)
INSERT INTO products (name, stock, price, category, description, enabled) VALUES
('iPhone 14', 15, 999.00, 'Electronics', 'Apple smartphone with iOS', TRUE),
('Samsung Galaxy S23', 20, 850.00, 'Electronics', 'Android flagship phone', TRUE),
('MacBook Air', 8, 1200.00, 'Computers', 'Apple lightweight laptop', TRUE),
('Dell XPS 13', 10, 1100.00, 'Computers', 'High-performance Windows laptop', TRUE),
('Sony WH-1000XM5', 25, 400.00, 'Audio', 'Noise-canceling headphones', TRUE),
('AirPods Pro', 30, 250.00, 'Audio', 'Wireless earbuds from Apple', TRUE),
('Logitech MX Master 3', 40, 99.00, 'Accessories', 'High-end wireless mouse', TRUE),
('Mechanical Keyboard', 35, 150.00, 'Accessories', 'RGB backlit mechanical keyboard', TRUE),
('Samsung 4K Monitor', 12, 300.00, 'Monitors', 'Ultra HD 4K display', TRUE),
('USB-C Hub', 50, 40.00, 'Accessories', 'Multi-port USB-C adapter', TRUE);

-- Insert sample data for 'shopping_cart'
INSERT INTO shopping_carts (client_id) VALUES
(1),
(2),
(3),
(4),
(5),
(6);

-- Insert sample data for 'products_shopping_cart' (Different clients with different products)
INSERT INTO products_shopping_carts (product_id, shopping_cart_id, quantity) VALUES
(1, 1, 1), -- John has 1 iPhone 14
(3, 1, 1), -- John also has 1 MacBook Air
(5, 2, 2), -- Alice has 2 Sony WH-1000XM5
(6, 2, 1), -- Alice also has 1 AirPods Pro
(2, 3, 1), -- Bob has 1 Samsung Galaxy S23
(4, 3, 1), -- Bob also has 1 Dell XPS 13
(8, 4, 3), -- Emma has 3 Mechanical Keyboards
(7, 5, 1), -- Charlie has 1 Logitech MX Master 3
(9, 6, 2), -- Olivia has 2 Samsung 4K Monitors
(10, 6, 4); -- Olivia also has 4 USB-C Hubs

-- Insert sample data for 'invoices'
INSERT INTO invoices (client_id) VALUES
(1),
(2),
(3),
(4),
(5),
(6);

-- Insert sample data for 'products_invoices' (Different products in different invoices)
INSERT INTO products_invoices (product_id, invoice_id, quantity, unit_price) VALUES
(1, 1, 1, 999.00), -- John bought 1 iPhone 14
(3, 1, 1, 1200.00), -- John bought 1 MacBook Air
(5, 2, 2, 400.00), -- Alice bought 2 Sony WH-1000XM5
(6, 2, 1, 250.00), -- Alice bought 1 AirPods Pro
(2, 3, 1, 850.00), -- Bob bought 1 Samsung Galaxy S23
(4, 3, 1, 1100.00), -- Bob bought 1 Dell XPS 13
(8, 4, 3, 150.00), -- Emma bought 3 Mechanical Keyboards
(7, 5, 1, 99.00), -- Charlie bought 1 Logitech MX Master 3
(9, 6, 2, 300.00), -- Olivia bought 2 Samsung 4K Monitors
(10, 6, 4, 40.00); -- Olivia bought 4 USB-C Hubs
