FROM openjdk:8
RUN mkdir /data
COPY . /data
WORKDIR /data
EXPOSE 8090
CMD ["bash"]

