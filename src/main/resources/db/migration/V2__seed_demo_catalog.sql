-- Fictional starter records for the local demo catalog.
INSERT INTO books (title, author, isbn, total_copies, available_copies, created_at, version) VALUES
('The Pragmatic Programmer', 'David Thomas and Andrew Hunt', '9780135957059', 3, 3, UTC_TIMESTAMP(6), 0),
('Clean Code', 'Robert C. Martin', '9780132350884', 2, 2, UTC_TIMESTAMP(6), 0),
('Designing Data-Intensive Applications', 'Martin Kleppmann', '9781449373320', 2, 2, UTC_TIMESTAMP(6), 0),
('Head First Java', 'Kathy Sierra and Bert Bates', '9780596009205', 2, 2, UTC_TIMESTAMP(6), 0),
('Effective Java', 'Joshua Bloch', '9780134685991', 1, 1, UTC_TIMESTAMP(6), 0),
('Refactoring', 'Martin Fowler', '9780134757599', 2, 2, UTC_TIMESTAMP(6), 0),
('The Mythical Man-Month', 'Frederick P. Brooks Jr.', '9780201835953', 1, 1, UTC_TIMESTAMP(6), 0),
('Grokking Algorithms', 'Aditya Bhargava', '9781617292231', 3, 3, UTC_TIMESTAMP(6), 0),
('Database System Concepts', 'Abraham Silberschatz, Henry Korth and S. Sudarshan', '9780078022159', 1, 1, UTC_TIMESTAMP(6), 0),
('Computer Networking: A Top-Down Approach', 'James Kurose and Keith Ross', '9780136681557', 1, 1, UTC_TIMESTAMP(6), 0);

INSERT INTO members (full_name, email, created_at) VALUES
('Demo Reader One', 'reader.one@example.test', UTC_TIMESTAMP(6)),
('Demo Reader Two', 'reader.two@example.test', UTC_TIMESTAMP(6)),
('Demo Reader Three', 'reader.three@example.test', UTC_TIMESTAMP(6));
