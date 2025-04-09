package org.thivernale.datingai.profiles;

public record Profile(
    String id,
    String firstName,
    String lastName,
    int age,
    String bio,
    String imageUrl,
    String ethnicity,
    Gender gender,
    String mbPersonalityType
) {
}
