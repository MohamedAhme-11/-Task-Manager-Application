CREATE TABLE IF NOT EXISTS event (
    id INT AUTO_INCREMENT PRIMARY KEY,
    summary VARCHAR(255),
    description TEXT,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    created_at TIMESTAMP
);

INSERT INTO event (summary, description, start_time, end_time, created_at) 
VALUES 
('Meeting with Bob', 'Discuss project updates', '2023-05-25T10:00:00', '2023-05-25T11:00:00', '2023-05-20T09:00:00'), 
('Lunch with Alice', 'Business lunch', '2023-05-25T12:00:00', '2023-05-25T13:00:00', '2023-05-20T09:30:00');
