create schema data_gaji;

use data_gaji;

create table pegawai (

NIP int (10),
Nama varchar (30),
Jabatan varchar (30),
Gaji_Pokok bigint (25),
Potongan bigint (20),
Tunjangan bigint (20),
Bonus bigint (20),
Gaji_Bersih bigint(20),

constraint NIP_PK primary key (NIP));