# Base image with Ubuntu
FROM ubuntu:latest

ARG ANDROID_BUILD_TOOLS=30.0.3
ARG ANDROID_COMPILE_SDK=33
ENV ANDROID_NDK_VERSION=r21e
ENV ANDROID_NDK_HOME=/opt/android-ndk

# Install dependencies
RUN apt-get update && \
    apt-get install -y apt-utils \
    openjdk-18-jdk \
    openjdk-18-jre-headless \
    software-properties-common \
    build-essential \
    libstdc++6 \
    libpulse0 \
    libglu1-mesa \
    openssh-server \
    unzip \
    curl \
    lldb \
    git && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Set up Android SDK
ENV ANDROID_HOME=/opt/android-sdk-linux
ENV ANDROID_SDK_ROOT=$ANDROID_HOME
ENV PATH=$PATH:$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools/bin:$ANDROID_HOME/cmdline-tools/latest/bin
RUN mkdir -p $ANDROID_HOME/cmdline-tools && \
    curl -sL https://dl.google.com/android/repository/commandlinetools-linux-7583922_latest.zip -o android-tools.zip && \
    unzip -q android-tools.zip -d $ANDROID_HOME/cmdline-tools && \
    rm android-tools.zip && \
    mv $ANDROID_HOME/cmdline-tools/cmdline-tools $ANDROID_HOME/cmdline-tools/latest

# Install Android SDK components
RUN yes | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager --licenses && \
    $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager --update && \
    $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager "build-tools;${ANDROID_BUILD_TOOLS}" "platforms;android-${ANDROID_COMPILE_SDK}" "platform-tools"

# Set up Android NDK
RUN curl -sL https://dl.google.com/android/repository/android-ndk-${ANDROID_NDK_VERSION}-linux-x86_64.zip -o /opt/android-ndk.zip && \
    unzip -q /opt/android-ndk.zip -d /opt && \
    rm -f /opt/android-ndk.zip

# Add Android NDK to PATH
ENV PATH=$PATH:$ANDROID_NDK_HOME

# Add Android NDK to PATH
ENV PATH=$PATH:/opt/android-ndk-r21e

# Install Go
RUN curl -sL https://golang.org/dl/go1.17.linux-amd64.tar.gz -o go.tar.gz && \
    tar -C /usr/local -xzf go.tar.gz && \
    rm go.tar.gz
ENV PATH=$PATH:/usr/local/go/bin

# Set up Gradle
ENV GRADLE_VERSION=7.4.2
ENV GRADLE_HOME=/opt/gradle
ENV PATH=$PATH:$GRADLE_HOME/bin
RUN curl -sL https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle.zip && \
    unzip -q gradle.zip -d /opt && \
    rm gradle.zip && \
    mv /opt/gradle-${GRADLE_VERSION} $GRADLE_HOME

# Set working directory
WORKDIR /app

# Open bash shell
CMD ["/bin/bash"]
