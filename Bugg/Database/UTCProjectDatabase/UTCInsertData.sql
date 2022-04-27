USE utcdemo;

INSERT INTO address
VALUES (1001,N'Sapa',               N'Lào Cai'),
       (1002,N'Y tý ',              N'Lào Cai'),
       (1003,N'Phố cổ Đồng Văn',    N'Hà Giang'),
       (1004,N'Thị TRấn Mèo Vạc',   N'Hà Giang'),
       (1005,N'Thác Dải Yếm',       N'Mộc Châu'),
       (1006,N'Hạ Long',            N'Quảng Ninh'),
       (1007,N'Bán Đảo Sơn Trà',    N'Đà Nẵng'),
       (1008,N'Bãi biển Mỹ Khê',    N'Đà Nẵng');


INSERT INTO `usertype`
VALUES (1,'ADMIN'),
       (2,'GUESTS');

INSERT INTO `guests`
VALUES (1,N'Hoàng',N'Việt','000012','0321654',N'hoangviet@gmail.com',   2,1001),
       (2,N'Thanh',N'Ngân','02335', '35353',N'thanhngan@gmail.com',     1,1006);

INSERT INTO `hotel`
VALUES (1,N'LuxuryHotel',           N'hotel@gmail.com',     N'lxury.vn',        N'Luxury là một trong những khách sạn ...',     12,1001),
       (2,N'Hoàng Kiều Homestay',   N'hoangkieu@gmail.com', N'hoangkieu.com',   N'Hoàng kiều là một trong những homestay ...',  16,1003),
       (3,N'Hải Đăng',              N'haidang@gmail.com',   N'haidang.vn',      N'Có vị trí đắc địa nhìn ra biển Hạ Long ...',  24,1006);

INSERT INTO `starrate`
VALUES (1,'image',1),
       (2,'image',1),
       (3,'image',2),
       (4,'image',3);

INSERT INTO `hotelimage`
VALUES ('1',1),
       ('3',1),
       ('2',2),
       ('4',3);

INSERT INTO `roomtype`
VALUES (1,N'luxury',        5300000.00, 'TRAVEL'),
       (2,N'phòng thường',  260000.00,  'RESORT');

INSERT INTO `roomratediscount`
VALUES (1,4.00,'2020-04-02 00:00:00','2025-04-02 00:00:00',1),
       (2,5.00,'2021-02-02 00:00:00','2026-02-02 00:00:00',2);

INSERT INTO `room`
VALUES (1,2,'BOOKING',  1,1),
       (2,3,'DRUM',     2,2);

INSERT INTO `booking`
VALUES (1,'2021-04-12 00:00:00',3,'2021-04-12 00:00:00','2021-04-15 00:00:00','BAKING',2,1,2,7200000.00,'PAID'),
       (2,'2022-04-24 00:00:00',2,'2022-04-24 00:00:00','2022-04-26 00:00:00','DIRECT',3,2,2,6340000.00,'UNPAID');

INSERT INTO `roombook`
VALUES (1,1,1),
       (2,2,2);

INSERT INTO `hotelservices`
VALUES (1,  N'Hoàng Phong',   230000.00,1),
       (2,  N'Hải Đăng',      342700.00,2),
       (3,  N'Hoàng Kiều',    320000.00,3);

INSERT INTO `userservices`
VALUES (1,1),
       (2,2);