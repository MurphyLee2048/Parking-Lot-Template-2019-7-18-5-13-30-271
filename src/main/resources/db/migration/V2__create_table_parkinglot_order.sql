create table `parking_order`(
`order_id` varchar(255) not null,
`parking_lot_name` varchar(255),
`car_license` varchar(255),
`entry_time` timestamp,
`leave_time` timestamp,
`status` boolean
)