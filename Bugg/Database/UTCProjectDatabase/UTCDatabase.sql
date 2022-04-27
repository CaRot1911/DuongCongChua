DROP DATABASE IF EXISTS UTCDemo;
CREATE DATABASE UTCDemo;

USE UTCDemo;

DROP TABLE IF EXISTS Address;
CREATE TABLE Address(
    addId       INT             NOT NULL PRIMARY KEY AUTO_INCREMENT,
    addCountry  NVARCHAR(100) ,
    addCity NVARCHAR(100)       NOT NULL
);

DROP TABLE IF EXISTS UserType;
CREATE TABLE UserType(
    utId    INT                     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    utName  ENUM('GUESTS','ADMIN')  NOT NULL DEFAULT 'GUESTS'
);

DROP TABLE IF EXISTS Guests;
CREATE TABLE `Guests`(
    gId         INT             NOT NULL PRIMARY KEY AUTO_INCREMENT,
    gFirstName  NVARCHAR(100)   NOT NULL ,
    gLastName   NVARCHAR(100)   NOT NULL ,
    gIdCard     NVARCHAR(100)   NOT NULL UNIQUE ,
    gCreditCard NVARCHAR(100)   NOT NULL UNIQUE ,
    gEmail      NVARCHAR(100)   NOT NULL UNIQUE ,
    g_utId      INT             NOT NULL ,
    g_addId     INT ,
    FOREIGN KEY (g_utId) REFERENCES UserType(utId) ,
    FOREIGN KEY (g_addId) REFERENCES Address(addId) ON DELETE SET NULL
);

DROP TABLE IF EXISTS `Hotel`;
CREATE TABLE `Hotel`(
    hId             INT             NOT NULL PRIMARY KEY AUTO_INCREMENT,
    hName           NVARCHAR(100)   NOT NULL UNIQUE ,
    hEmailAddress   NVARCHAR(100)   NOT NULL UNIQUE ,
    hWebsite        NVARCHAR(100)   NOT NULL UNIQUE ,
    hDescription    NVARCHAR(100)   NOT NULL ,
    hRoomCount      INT             NOT NULL ,
    h_addId         INT ,
    FOREIGN KEY (h_addId) REFERENCES Address(addId) ON DELETE SET NULL
);

DROP TABLE IF EXISTS StarRate;
CREATE TABLE StarRate(
    srId    INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    srImage NVARCHAR(100) ,
    sr_hId  INT NOT NULL ,
    FOREIGN KEY (sr_hId) REFERENCES Hotel(hId) ON DELETE CASCADE
);

DROP TABLE IF EXISTS HotelImage;
CREATE TABLE HotelImage(
    hiImageName NVARCHAR(100)   NOT NULL PRIMARY KEY ,
    hi_hId      INT             NOT NULL ,
    FOREIGN KEY (hi_hId) REFERENCES Hotel(hId)
);

DROP TABLE IF EXISTS RoomType;
CREATE TABLE RoomType(
    rtId            INT                                         NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    rtName          NVARCHAR(100)                               NOT NULL ,
    rtCost          DECIMAL(10,2)                               NOT NULL ,
    rtDescription   ENUM('TRAVEL','GO ON BUSSINESS','RESORT')   NOT NULL DEFAULT 'TRAVEL'
);

DROP TABLE IF EXISTS RoomRateDiscount;
CREATE TABLE RoomRateDiscount(
  rrdId         INT             NOT NULL PRIMARY KEY AUTO_INCREMENT ,
  rrdRate       DECIMAL(10,2)   NOT NULL ,
  rrdStartMonth DATETIME        NOT NULL ,
  rrdEndMonth   DATETIME        NOT NULL ,
  rrd_rtId      INT ,
  FOREIGN KEY (rrd_rtId) REFERENCES RoomType(rtId) ON DELETE SET NULL
);

DROP TABLE IF EXISTS Room;
CREATE TABLE Room(
    rId         INT                     NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    rNumber     INT                     NOT NULL ,
    rStatus     ENUM ('DRUM','BOOKING') NOT NULL DEFAULT 'DRUM' ,
    r_rtId      INT ,
    r_hId       INT ,
    FOREIGN KEY (r_rtId) REFERENCES RoomType(rtId) ON DELETE SET NULL ,
    FOREIGN KEY (r_hId) REFERENCES Hotel(hId) ON DELETE SET NULL
);

DROP TABLE IF EXISTS Booking;
CREATE TABLE Booking(
    bId             INT                     NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    bDate           DATETIME                NOT NULL DEFAULT NOW() ,
    bDurationOfStay INT ,
    bCheckInDate    DATETIME                NOT NULL ,
    bCheckOutDate   DATETIME                NOT NULL ,
    bTypePayment    ENUM('BAKING','DIRECT') NOT NULL DEFAULT 'DIRECT' ,
    bTotalRoom      TINYINT                 NOT NULL DEFAULT 1 ,
    b_hId           INT                     NOT NULL ,
    b_gId           INT                     NOT NULL ,
    bTotalAmount    DECIMAL(10,2) ,
    bStatus         ENUM('UNPAID','PAID')   NOT NULL DEFAULT 'UNPAID' ,
    FOREIGN KEY (b_hId) REFERENCES Hotel(hId) ,
    FOREIGN KEY (b_gId) REFERENCES Guests(gId)

);

DROP TABLE IF EXISTS RoomBook;
CREATE TABLE RoomBook(
    rbId        INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    rb_rId      INT NOT NULL ,
    rb_bId      INT NOT NULL ,
    FOREIGN KEY (rb_rId) REFERENCES Room(rId) ON DELETE CASCADE ,
    FOREIGN KEY (rb_bId) REFERENCES Booking(bId) ON DELETE CASCADE
);

DROP TABLE IF EXISTS HotelServices;
CREATE TABLE HotelServices(
    hsId        INT             NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    hsName      NVARCHAR(100)   NOT NULL ,
    hsCost      DECIMAL(10,2)   NOT NULL ,
    hs_hId      INT             NOT NULL ,
    FOREIGN KEY (hs_hId) REFERENCES Hotel(hId) ON DELETE CASCADE
);

DROP TABLE IF EXISTS UserServices;
CREATE TABLE UserServices (
    us_hsId     INT NOT NULL ,
    us_bId      INT NOT NULL ,
    PRIMARY KEY (us_hsId,us_bId) ,
    FOREIGN KEY (us_bId) REFERENCES Booking(bId) ON DELETE CASCADE ,
    FOREIGN KEY (us_hsId) REFERENCES HotelServices(hsId) ON DELETE CASCADE
);


