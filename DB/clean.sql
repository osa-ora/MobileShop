select 'alter system kill session ''' || sid || ',' || serial# || ''';' from v$session where username = 'orders';
drop user orders cascade;
create user orders identified by orders;
grant all privileges to orders;