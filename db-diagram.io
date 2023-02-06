
Table student as S {
  name varchar [pk]
}

Table exam as E{
  id int [pk]
  name varchar
  continent_name varchar
 }

Table question as Q{
  id int [pk]
  answer_A varchar
  answer_B varchar
  answer_C varchar
  correct_answer bit
 }
// Creating references
// > many-to-one; < one-to-many; - one-to-one; <> many-to-many
Ref: S.name <> E.id
Ref: "Q"."id" > "E"."id"

// students can have many exams