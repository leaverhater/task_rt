CREATE TABLESPACE RT
create user vadya identified by enjoybmstu DEFAULT TABLESPACE RT
QUOTA 10G ON RT QUOTA 2M ON TEMP;
grant CREATE SESSION, ALTER SESSION, CREATE TABLE, ALTER ANY TABLE, CREATE TRIGGER to vadya;