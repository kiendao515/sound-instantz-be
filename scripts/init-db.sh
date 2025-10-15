#!/bin/bash

# Wait for PostgreSQL to be ready
MAX_TRIES=30
TRIES=0

until PGPASSWORD=postgres psql -h localhost -U postgres -c '\q' 2>/dev/null; do
  TRIES=$((TRIES+1))
  if [ $TRIES -eq $MAX_TRIES ]; then
    echo "Could not connect to PostgreSQL after $MAX_TRIES attempts"
    exit 1
  fi
  echo "Waiting for PostgreSQL to be ready... ($TRIES/$MAX_TRIES)"
  sleep 2
done

# Create database if it doesn't exist
PGPASSWORD=postgres psql -h localhost -U postgres -tc "SELECT 1 FROM pg_database WHERE datname = 'soundinstantz'" | grep -q 1 || \
PGPASSWORD=postgres psql -h localhost -U postgres -c "CREATE DATABASE soundinstantz"

# Import schema and data
PGPASSWORD=postgres psql -h localhost -U postgres -d soundinstantz -f init-scripts/01-init.sql
PGPASSWORD=postgres psql -h localhost -U postgres -d soundinstantz -f init-scripts/02-sample-data.sql
