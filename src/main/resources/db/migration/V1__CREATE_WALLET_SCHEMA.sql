CREATE table users(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(150) NOT NULL ,
    last_name VARCHAR(150) NOT NULL,
    gender VARCHAR(1) check (gender IN ('M', 'F')),
    email VARCHAR(255) NOT NULL UNIQUE ,
    birthday date,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(30) NOT NULL UNIQUE ,
    document CHAR(14) NOT NULL UNIQUE,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'BLOCKED', 'PENDING_VERIFICATION')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
    );

CREATE table wallets(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL UNIQUE references users(id) ON DELETE CASCADE,
    balance NUMERIC(19,2) NOT NULL DEFAULT 0.00,
    blocked_balance NUMERIC(19,2) NOT NULL DEFAULT 0.00,
    currency CHAR(3) NOT NULL DEFAULT 'BRL' CHECK (currency IN ('BRL','USD','EUR')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE table transactions(
    id UUID PRIMARY KEY default  gen_random_uuid(),
    sender_wallet_id UUID NOT NULL references wallets(id) ,
    receiver_wallet_id UUID NOT NULL references wallets(id),
    amount NUMERIC(19,2) NOT NULL check ( amount > 0 ),
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING'
        CHECK (status IN (
                          'PENDING',
                          'COMPLETED',
                          'FAILED',
                          'CANCELED'
            )),
    description VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP,
    CHECK (sender_wallet_id <> receiver_wallet_id)
);