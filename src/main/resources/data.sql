insert into survey (name, description) values ('Encuesta de satisfacción en la atención','Esta encuesta busca determinar el nivel de satisfacción en la atención recibida');
insert into question (survey_id, description, open) values (1, 'De las siguientes opciones ¿cómo califica el nivel de atención?:', false);
insert into answer (question_id, description) values (1,'Excelente');
insert into answer (question_id, description) values (1,'Buena');
insert into answer (question_id, description) values (1,'Regular');
insert into answer (question_id, description) values (1,'Mala');
insert into answer (question_id, description) values (1,'Deficiente');
insert into question (survey_id, description, open) values (1, 'De las siguientes opciones ¿cómo califica el rapidez en atención?:', false);
insert into answer (question_id, description) values (2,'Muy rápido');
insert into answer (question_id, description) values (2,'Normal');
insert into answer (question_id, description) values (2,'Regular');
insert into answer (question_id, description) values (2,'Muy Lento');
insert into question (survey_id, description, open) values (1, '¿Recomendaría el servicio a alguien más?:', false);
insert into answer (question_id, description) values (3,'SI');
insert into answer (question_id, description) values (3,'NO');
insert into question (survey_id, description, open) values (1, 'Sugerencias o comentarios:', true);
insert into customer (name, email) values ('Maria Suarez', 'm.suarez@gmail.com')

