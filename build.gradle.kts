plugins {
    id("me.champeau.gradle.jmh") version "0.5.2"
}

group = "com.wrongwrong"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter", version = "5.7.0") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
    // https://qiita.com/wrongwrong/items/16fa10a7f78a31830ed8
    jmhJar {
        exclude("META-INF/versions/9/module-info.class")
    }
}

jmh {
    failOnError = true
    isIncludeTests = false

    resultFormat = "CSV"
}
