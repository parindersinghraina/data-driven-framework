version: "2"
services:
  hub:
    image: selenium/hub:3.141.59
    expose:
      - "4444"
    logging:
      driver: none
    environment:
      GRID_MAX_SESSION: 5

  chrome:
    image: selenium/node-chrome:3.141.59
    volumes:
      - /dev/shm:/dev/shm
    environment:
      HUB_HOST: hub
      NODE_MAX_INSTANCES: 5
      NODE_MAX_SESSION: 5
    logging:
      driver: none
    depends_on:
      - hub

  test_runner:
    image: openjdk:8-jdk
    volumes:
      - .:/tests
    working_dir: /tests
    environment:
      - AUTOMATED_TEST_ENV_CONFIG=${AUTOMATED_TEST_ENV_CONFIG}
      - AUTOMATED_TEST_WD_CONFIG=docker-compose
      - AUTOMATED_TEST_SUITE=${AUTOMATED_TEST_SUITE}
    command: bash -c "./gradlew clean build -DAUTOMATED_TEST_SUITE=$${AUTOMATED_TEST_SUITE}; chmod -R 777 build"
    depends_on:
      - chrome
      - hub