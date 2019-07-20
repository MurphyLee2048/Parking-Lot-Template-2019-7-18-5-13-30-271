create table `parkinglot`(
`parking_lot_name` varchar(255) not null,
`capacity` int check capacity > 0,
`location` varchar(255)
)