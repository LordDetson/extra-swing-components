package by.babanin.ext.component.logger;

import by.babanin.ext.message.TranslateCode;
import by.babanin.ext.message.Translator;

public enum LogMessageType {
    ERROR(TranslateCode.ERRORS),
    WARNING(TranslateCode.WARNINGS),
    INFORMATION(TranslateCode.INFORMATION),
    ;

    private final String captionCode;

    LogMessageType(String captionCode) {
        this.captionCode = captionCode;
    }

    @Override
    public String toString() {
        return Translator.toLocale(captionCode);
    }

    public String getCaption() {
        return Translator.toLocale(captionCode);
    }
}
