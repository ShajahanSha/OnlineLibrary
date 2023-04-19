package com.online.library.domain.cqrs.validator;


public class ValidationMessage {


    public static final String ERROR_REQUIRED = "must have value";
    public static final String ERROR_AMOUNT_MUST_LESS_OR_EQUAL_TO_TWO_DECIMALS_ONLY = "must be less of equal to two decimals only";
    public static final String ERROR_COLLECTION_SIZE_BETWEEN = "must be between %d and %d items in collection";
    public static final String ERROR_CHAR_LENGTH = "must be %d character in length";
    public static final String ERROR_CHAR_LENGTH_BETWEEN = "must be between %d and %d characters in length";
    public static final String ERROR_INVALID_ID_FORMAT = "must be in XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX format";
    public static final String ERROR_INVALID_DATE_FORMAT = "invalid date format, format should be DD:MMM:YYYY";
    public static final String ERROR_INVALID_STATUS = "invalid status";
    public static final String ERROR_INVALID_VALUE = "invalid value";
    public static final String ERROR_INVALID_FORMAT = "invalid format";
    public static final String ERROR_LATER_TODAY_DATE = "must be later than today";
    public static final String ERROR_NUMBER_GREATER_THAN = "must be greater than %d";
    public static final String ERROR_NUMBER_GREATER_THAN_EQUAL_TO = "must be greater than or equal to %d";
    public static final String ERROR_NUMBER_LESS_THAN = "must be less than %d";
    public static final String ERROR_NUMBER_LESS_THAN_EQUAL_TO = "must be less than or equal to %d";
    public static final String ERROR_INVALID_URL_FORMAT = "invalid URL format";
    public static final String ERROR_INVALIDNVOICEDATE_WEEKDAY = "must be a weekday";
    public static final String ERROR_BOOLEAN_MUST_TRUE = "must be true";
    public static final String ERROR_NUMBER_BETWEEN = "must be between %d and %d.";


    public static final String ERROR_CREDITCARD_EXPIRY_INVALID = "invalid due to expiry date.";
    public static final String ERROR_CREDITCARD_NUMBER_INVALID = "format is invalid.";
    public static final String ERROR_INTEGER_ONLY = "must be integer only.";
    public static final String ERROR_NUMBER_INVALID = "is a invalid number.";
    public static final String ERROR_INTEGER_LENGTH = "must be %d digits in length.";
    public static final String ERROR_BANK_INVALID_CHECKSUM = "checksum is invalid.";
    public static final String ERROR_INFO_SERVICE_DOWN = "info service is down.";

    public static final String ERROR_INVALID_SOURCE_MERCHANT = "source merchant can not be empty or null";
    public static final String ERROR_INVALID_MERCHANT_BATCH_ID  = "merchant batch id can not be empty or null";
    public static final String ERROR_INVALID_MERCHANT = "merchant code not be empty or null";
    public static final String ERROR_INVALID_RECONCILIATION_STATUS = "Reconciliation status can not be empty or null";
    public static final String ERROR_INVALID_WALLET_ID = "wallet id not be empty or null";
    public static final String ERROR_INVALID_BENEFICIARY = "Provided beneficiary does not associated with provided merchant";
    public static final String ERROR_INVALID_SENDER = "Invalid sender code";
    public static final String ERROR_INVALID_SENDER_ORDER_ID = "Invalid sender order id";
    public static final String ERROR_INVALID_TRANSACTION_AMOIUNT = "Invalid transaction amount";
    public static final String ERROR_INVALID_MODULE = "Module should not be empty or null";
    public static final String ERROR_INVALID_SENDER_REFERENCE = "sender reference should not be empty or null";
    public static final String ERROR_INVALID_ACTION = "receiver action should not be empty or null";;
}
