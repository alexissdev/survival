package dev.notcacha.survival.api.tag.creator;

import dev.notcacha.survival.api.tag.Tag;
import dev.notcacha.survival.api.util.Validate;

public interface TagCreator {

    interface TagProperties {

        /**
         * @return The id property.
         */

        String getId();

        /**
         * @return The prefix property.
         */

        String getPrefix();

        /**
         * @return The suffix property.
         */

        String getSuffix();

        /**
         * @return color code from player name property.
         */

        char getColorCodeFromPlayerName();

        /**
         * @return New {@link TagProperties} of array.
         */

        static TagProperties ofArray(Object... properties) {
            Validate.nonNull(properties, "The properties array of TagProperties is null.");

            if (properties.length < 3) {
                throw new IllegalArgumentException("Invalid array properties of TagProperties creator.");
            }

            return new TagProperties() {
                @Override
                public String getId() {
                    return (String) properties[0];
                }

                @Override
                public String getPrefix() {
                    return (String) properties[1];
                }

                @Override
                public String getSuffix() {
                    return (String) properties[2];
                }

                @Override
                public char getColorCodeFromPlayerName() {
                    return (char) properties[3];
                }
            };
        }

    }

    /**
     * @return New {@link Tag} of {@link TagProperties}
     */

    static Tag create(TagProperties tagProperties) {
        Validate.nonNull(tagProperties, "The tag properties of tag creator is null.");

        return new Tag() {

            private String prefix = tagProperties.getPrefix();
            private String suffix = tagProperties.getSuffix();
            private char colorCode = tagProperties.getColorCodeFromPlayerName();

            @Override
            public String getId() {
                return tagProperties.getId();
            }

            @Override
            public String getPrefix() {
                return prefix;
            }

            @Override
            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            @Override
            public String getSuffix() {
                return suffix;
            }

            @Override
            public void setSuffix(String suffix) {
                this.suffix = suffix;
            }

            @Override
            public char getColorCodeFromPlayerName() {
                return colorCode;
            }

            @Override
            public void setColorCodeFromPlayerName(char colorCode) {
                this.colorCode = colorCode;
            }
        };
    }

}
