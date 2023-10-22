create table student (
    id uuid primary key default gen_random_uuid(),
    name text not null,
    unit text not null,
    index integer
)