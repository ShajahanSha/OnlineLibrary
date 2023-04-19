package com.online.library.domain.cqrs.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseValidator {

    private static final Logger logger = LoggerFactory.getLogger(BaseValidator.class);
    enum ValidateAttributes {
        proxy,
        vpn,
        active_vpn,
        tor,
        active_tor,
        fraud_score,
        risk_score,
        recent_abuse,
        abuse_velocity,
        bot_status,
        country_code,
        allowed_bin_country,
        banned_bin_country,
        is_crawler,
        connection_type
    }

    private final List<CustomException> exceptions;
    private BaseValidator(final Validator validator) {
        exceptions = new ArrayList<CustomException>(validator.exceptionsAll);
    }

    public boolean hasException() {
        return exceptions.isEmpty();
    }

    public List<CustomException> getAllExceptions() {
        return exceptions;
    }

    public void throwFirstExceptionIfAny() throws CustomException {
        if (!exceptions.isEmpty()) {
            throw exceptions.get(0);
        }
    }

    public static class Validator {
        private final List<CustomException> exceptionsAll = new ArrayList<CustomException>();

        public Validator validateProxy(HashMap map) {
            if (isValidData(map))  {
                Boolean apiValue = Boolean.parseBoolean(map.get("apiValue").toString());
                Boolean configuredValue = Boolean.parseBoolean(map.get("dbValue").toString());
                if (apiValue == configuredValue) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.proxy.name(), ValidateAttributes.proxy.name()));
                }
            }
            return this;
        }
        public Validator validateVpn(HashMap map) {
            if (isValidData(map))  {
                Boolean apiValue = Boolean.parseBoolean(map.get("apiValue").toString());
                Boolean configuredValue = Boolean.parseBoolean(map.get("dbValue").toString());
                if (apiValue == configuredValue) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.vpn.name(), ValidateAttributes.vpn.name()));
                }
            }
            return this;
        }
        public Validator validateActiveVpn(HashMap map) {
            if (isValidData(map))  {
                Boolean apiValue = Boolean.parseBoolean(map.get("apiValue").toString());
                Boolean configuredValue = Boolean.parseBoolean(map.get("dbValue").toString());
                if (apiValue == configuredValue) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.active_vpn.name(), ValidateAttributes.active_vpn.name()));
                }
            }
            return this;
        }
        public Validator validateTor(HashMap map) {
            if (isValidData(map))  {
                Boolean apiValue = Boolean.parseBoolean(map.get("apiValue").toString());
                Boolean configuredValue = Boolean.parseBoolean(map.get("dbValue").toString());
                if (apiValue == configuredValue) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.tor.name(), ValidateAttributes.tor.name()));
                }
            }
            return this;
        }
        public Validator validateActiveTor(HashMap map) {
            if (isValidData(map))  {
                Boolean apiValue = Boolean.parseBoolean(map.get("apiValue").toString());
                Boolean configuredValue = Boolean.parseBoolean(map.get("dbValue").toString());
                if (apiValue == configuredValue) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.active_tor.name(), ValidateAttributes.active_tor.name()));
                }
            }
            return this;
        }
        public Validator validateFraudScore(HashMap map) {
            if (isValidData(map)) {
                Long apiValue = Long.parseLong(map.get("apiValue").toString());
                Long configuredValue = Long.parseLong(map.get("dbValue").toString());
                if (apiValue.compareTo(configuredValue) > 0) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.fraud_score.name(), ValidateAttributes.fraud_score.name()));
                }
            }
            return this;
        }
        public Validator validateRiskScore(HashMap map) {
            if (isValidData(map)) {
                Long apiValue = Long.parseLong(map.get("apiValue").toString());
                Long configuredValue = Long.parseLong(map.get("dbValue").toString());
                if (apiValue.compareTo(configuredValue) > 0) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.risk_score.name(), ValidateAttributes.risk_score.name()));
                }
            }
            return this;
        }
        public Validator validateIfRecentAbused(HashMap map) {
            if (isValidData(map)) {
                Boolean apiValue = Boolean.parseBoolean(map.get("apiValue").toString());
                Boolean configuredValue = Boolean.parseBoolean(map.get("dbValue").toString());
                if (apiValue == configuredValue) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.recent_abuse.name(), ValidateAttributes.recent_abuse.name()));
                }
            }
            return this;
        }
        //abuse_velocity : Values can be "high", "medium", "low", or "none". "Premium required." for testing environment or for free account
        public Validator validateAbuseVelocity(HashMap map) {
            if (isValidData(map)) {
                String apiValue = map.get("apiValue").toString();
                String configuredValue = map.get("dbValue").toString();
                if (configuredValue.contains(apiValue)) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.abuse_velocity.name(), ValidateAttributes.abuse_velocity.name()));
                }
            }
            return this;
        }
        //connection_type : Classification of the IP address connection type as "Residential", "Corporate", "Education", "Mobile", or "Data Center".
        public Validator validateConnectionType(HashMap map) {
            if (isValidData(map)) {
                String apiValue = map.get("apiValue").toString();
                String configuredValue = map.get("dbValue").toString();
                if (!configuredValue.contains(apiValue)) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.connection_type.name(), ValidateAttributes.connection_type.name()));
                }
            }
            return this;
        }
        public Validator validateBotStatus(HashMap map) {
            if (isValidData(map)) {
                Boolean apiValue = Boolean.parseBoolean(map.get("apiValue").toString());
                Boolean configuredValue = Boolean.parseBoolean(map.get("dbValue").toString());
                if (apiValue == configuredValue) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.bot_status.name(), ValidateAttributes.bot_status.name()));
                }
            }
            return this;
        }
        //DB will have country entries which are banned. If no restriction on any country then this row will contain empty value.
        public Validator validateBannedCountry(HashMap map) {
            if (isValidData(map)) {
                String apiValue = map.get("apiValue").toString();
                String configuredValue = map.get("dbValue").toString();
                if (configuredValue.contains(apiValue)) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.country_code.name(), ValidateAttributes.country_code.name()));
                }
            }
            return this;
        }
        //DB will have country entries which are allowed based on the bin_country returned from response for a ccd transactions.
        public Validator validateAllowedBinCountries(HashMap map) {
            if (isValidData(map)) {
                String apiValue = map.get("apiValue").toString();
                String configuredValue = map.get("dbValue").toString();
                if (!configuredValue.contains(apiValue)) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.allowed_bin_country.name(), ValidateAttributes.allowed_bin_country.name()));
                }
            }
            return this;
        }
        //DB will have bin country entries which are banned. If no restriction on any country then this row will contain empty value. The comparistion will happen based on bin_country values
        public Validator validateBannedBinCountries(HashMap map) {
            if (isValidData(map)) {
                String apiValue = map.get("apiValue").toString();
                String configuredValue = map.get("dbValue").toString();
                if (configuredValue.contains(apiValue)) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.banned_bin_country.name(), ValidateAttributes.banned_bin_country.name()));
                }
            }
            return this;
        }
        public Validator validateIfCrawler(HashMap map) {
            if (isValidData(map)) {
                Boolean apiValue = Boolean.parseBoolean(map.get("apiValue").toString());
                Boolean configuredValue = Boolean.parseBoolean(map.get("dbValue").toString());
                if (apiValue == configuredValue) {
                    exceptionsAll.add(new CustomException(ValidateAttributes.is_crawler.name(), ValidateAttributes.is_crawler.name()));
                }
            }
            return this;
        }

        private Boolean isRiskScoreHigherThanBothFraudScoreAndConfiguredRiskScore(Long fraudApiValue, Long fraudConfiguredValue, Long riskApiValue, Long riskConfiguredValue) {
            if (riskApiValue.compareTo(fraudApiValue) > 0 && riskApiValue.compareTo(riskConfiguredValue) > 0) {
                exceptionsAll.add(new CustomException(ValidateAttributes.risk_score.name(), ValidateAttributes.risk_score.name()));
                return true;
            }
            return false;
        }

        private Boolean isFraudScoreHigherThanBothRiskScoreAndConfiguredFraudScore(Boolean isRiskExist, Long fraudApiValue, Long fraudConfiguredValue, Long riskApiValue, Long riskConfiguredValue) {
            if (!isRiskExist && fraudApiValue.compareTo(riskApiValue) > 0 && fraudApiValue.compareTo(fraudConfiguredValue) > 0) {
                exceptionsAll.add(new CustomException(ValidateAttributes.fraud_score.name(), ValidateAttributes.fraud_score.name()));
                return true;
            }
            return false;
        }

        private Boolean isRiskScoreAndFraudScoreAreSameAndRiskScoreIsMoreThanConfiguredRiskScore(Boolean isFraudExist, Long fraudApiValue, Long fraudConfiguredValue, Long riskApiValue, Long riskConfiguredValue) {
            if (!isFraudExist && riskApiValue.compareTo(fraudApiValue) == 0 && riskApiValue.compareTo(riskConfiguredValue) > 0) {
                return true;
            }
            return false;
        }

        public Boolean isValidData(HashMap map) {
            return ( !CollectionUtils.isEmpty(map) && map.get("apiValue") != null && map.get("dbValue") != null) ? true : false;
        }

        public BaseValidator validate() {
            return new BaseValidator(this);
        }
    }
}
