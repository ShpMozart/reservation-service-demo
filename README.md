This project is a simple backend example that demonstrates how to prevent double booking in a seat reservation system using Redis, Kafka, and Optimistic Locking with PostgreSQL.

It simulates a high-concurrency environment where multiple users may attempt to reserve the same seat. The system is designed to handle this by:

✅ Using Redis to temporarily lock and cache seat reservations for fast access.(Reserve service)

✅ Using Kafka to process confirmed reservations asynchronously after payment.(Confirm service)

✅ Applying Optimistic Locking in PostgreSQL to ensure data consistency and prevent race conditions at the database level.(Confirm service)
