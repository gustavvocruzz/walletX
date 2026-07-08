ALTER table wallets ADD COLUMN wallet_status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE';
ALTER TABLE wallets ADD CONSTRAINT chk_wallet_status CHECK (wallet_status IN ('ACTIVE', 'BLOCKED'));