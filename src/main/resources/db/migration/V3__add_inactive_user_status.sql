ALTER TABLE users
DROP CONSTRAINT users_status_check;

ALTER TABLE users
    ADD CONSTRAINT users_status_check
        CHECK (
            status IN (
                       'ACTIVE',
                       'BLOCKED',
                       'PENDING_VERIFICATION',
                       'INACTIVE'
                )
            );