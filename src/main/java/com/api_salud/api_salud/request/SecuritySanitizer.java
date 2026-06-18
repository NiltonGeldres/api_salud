package com.api_salud.api_salud.request;

public class SecuritySanitizer {
	private SecuritySanitizer() {}

    public static String sanitize(String input) {
        if (input == null) return null;
        return input.replaceAll("<[^>]*>", "")
                    .replace("javascript:", "")
                    .trim();
    }
}
