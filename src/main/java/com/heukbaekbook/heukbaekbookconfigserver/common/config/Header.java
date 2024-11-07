package com.heukbaekbook.heukbaekbookconfigserver.common.config;

public record Header(int resultCode, String resultMessage, boolean isSuccessful) {
}
