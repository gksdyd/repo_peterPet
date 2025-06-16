# OpenJDK 21 base image (Debian 기반)
FROM openjdk:21-jdk

# 프로젝트 디렉토리명 환경 변수
ENV project="peterpet"

# 시스템 시간대 설정
ENV TZ=Asia/Seoul

# 필수 패키지 설치 + ffmpeg 설치
RUN apt-get update && \
    apt-get install -y tzdata ffmpeg && \
    ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && \
    dpkg-reconfigure -f noninteractive tzdata && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# 프로젝트 디렉토리 생성
RUN mkdir /${project}

# JAR 파일 복사
COPY build/libs/*.jar /${project}/${project}.jar

# JAR 실행
ENTRYPOINT ["java", "-jar", "/peterpet/peterpet.jar"]
