#!/bin/bash

echo "Checking categories table:"
PGPASSWORD=postgres psql -h localhost -U postgres -d soundinstantz -c "SELECT * FROM categories;"

echo -e "\nChecking sounds table:"
PGPASSWORD=postgres psql -h localhost -U postgres -d soundinstantz -c "SELECT id, name, category_id FROM sounds;"
