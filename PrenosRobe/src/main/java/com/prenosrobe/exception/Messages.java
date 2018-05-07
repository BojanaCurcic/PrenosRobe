package com.prenosrobe.exception;

public class Messages
{
	public static final String INVALID_DATA = "Invalid data.";

	public static final String EMAIL_USED = "E-mail adresa je već u upotrebi.";
	public static final String EMAIL_FORMAT = "Format e-mail adrese nije validan.";
	public static final String USERNAME_USED = "Korisničko ime je već u upotrebi.";
	public static final String PHONE_NUMBER_USED = "Telefonski broj je već u upotrebi.";

	public static final String UNKNOWN_CLAIMER_OFFER = "\"claimerOffer\" is unknown. ";
	public static final String UNKNOWN_DRIVER_OFFER = "\"driverOffer\" is unknown. ";
	public static final String UNKNOWN_DRIVER_OFFER_USER_VEHICLE = "\"driverOffer.userVehicle\" is unknown. ";
	public static final String UNKNOWN_DRIVER_OFFER_USER_VEHICLE_USER = "\"driverOffer.userVehicle.user\" is unknown. ";
	public static final String UNKNOWN_DRIVER_OFFER_USER_VEHICLE_VEHICLE = "\"driverOffer.userVehicle.vehicle\" is unknown. ";
	public static final String UNKNOWN_OFFER_STATUS = "\"offerStatus\" is unknown. ";
	public static final String UNKNOWN_USER = "\"user\" is unknown. ";
	public static final String UNKNOWN_USER_VEHICLE_USER = "\"userVehicle.user\" is unknown. ";
	public static final String UNKNOWN_USER_VEHICLE_VEHICLE_VEHICLE_TYPE = "\"userVehicle.vehicle.vehicleType\" is unknown. ";
	public static final String UNKNOWN_VEHICLE = "\"vehicle\" is unknown. ";

	public static final String DELIVERED_IS_NULL = "\"delivered\" may not be null. ";
	public static final String DELIVERED_UNDAMAGED_IS_NULL = "\"deliveredUndamaged\" may not be null. ";
	public static final String CORRECTLY_PAID_IS_NULL = "\"correctlyPaid\" may not be null. ";

	public static final String SUCCESSFULLY_UPLOADED = "Successfully uploaded file: ";
	public static final String NOT_UPLOADED = "Not uploaded file: ";

	public static final String INIT_STORAGE = "Could not initialize storage. ";
	public static final String STORE_FILE = "Failed to store file: ";
	public static final String STORE_EMPTY_FILE = "Failed to store empty file: ";
	public static final String STORE_FILE_WITH_RELATIVE_PATH = "Cannot store file with relative path outside current directory: ";
	public static final String READ_STORED_FILES = "Failed to read stored files. ";
	public static final String READ_FILE = "Could not read file: ";
}
