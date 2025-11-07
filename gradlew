#!/usr/bin/env sh

# Ensure the script has executable permissions
if [ ! -x "$0" ]; then
    echo "Error: gradlew script is not executable. Run 'chmod +x gradlew'."
    exit 1
fi

# Determine the Java command
if [ -n "$JAVA_HOME" ] && [ -x "$JAVA_HOME/bin/java" ]; then
    JAVACMD="$JAVA_HOME/bin/java"
elif command -v java >/dev/null 2>&1; then
    JAVACMD=java
else
    echo "Error: JAVA_HOME is not set and 'java' command not found in PATH."
    exit 1
fi

APP_HOME=$(dirname "$0")
APP_NAME=$(basename "$0")

# If you want to use a specific version of Gradle, define it here:
GRADLE_VERSION=8.4
GRADLE_OPTS="-Dorg.gradle.daemon=true -Dorg.gradle.jvmargs=-Xmx1536m"

# Execute Gradle with the wrapper jar
"$JAVACMD" $GRADLE_OPTS -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"

