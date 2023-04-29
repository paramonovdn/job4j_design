create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + (price * 0.2)
        where id in (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();


create or replace function tax2()
     returns trigger as
$$
    BEGIN
       new.price = new.price + (new.price * 0.2);
       RETURN new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger2
    before insert on products    
    for each row
    execute procedure tax2();


CREATE OR REPLACE FUNCTION history_of_price_insert()
  RETURNS trigger AS
$$
   BEGIN
      INSERT INTO history_of_price (name, price, date)
      VALUES(NEW.name, NEW.price, current_date);
      RETURN NEW;
   END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER history_of_price_insert_trigger
  AFTER INSERT
  ON products
  FOR EACH ROW
  EXECUTE PROCEDURE history_of_price_insert();