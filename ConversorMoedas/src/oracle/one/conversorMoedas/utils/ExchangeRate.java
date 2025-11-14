package oracle.one.conversorMoedas.utils;

public record ExchangeRate(String time_last_update_utc,
                           String time_next_update_utc,
                           String base_code,
                           String target_code,
                           String conversion_rate) {
}
