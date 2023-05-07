create or replace procedure delete_data_to_id(u_id integer)
language 'plpgsql'
as $$
    BEGIN
        delete from products where id = u_id;
    END;
$$;

create or replace function f_delete_data_to_zero_count()
returns void
language 'plpgsql'
as
$$
    begin
        delete from products where count = 0;
    end;
$$;
