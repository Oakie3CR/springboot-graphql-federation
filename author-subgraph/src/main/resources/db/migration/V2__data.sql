insert into author(name)
values
   ('Jane Austen'),
   ('Charles Dickens'),
   ('Mary Shelley'),
   ('Louisa May Alcott'),
   ('H. G. Wells'),
   ('Lewis Carroll');

insert into media_type(code, description)
values
   ('b', 'Book'),
   ('m', 'Movie');

insert into media(media_id, media_type_code, author_id)
values
   (1, 'b', 1),
   (2, 'b', 1),
   (3, 'b', 2),
   (4, 'b', 2),
   (5, 'b', 3),
   (6, 'b', 4),
   (7, 'b', 5),
   (8, 'b', 5),
   (9, 'b', 6),
   (10, 'm', 6)
;
