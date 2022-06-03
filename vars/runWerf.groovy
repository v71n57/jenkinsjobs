#!/usr/bin/env groovy
def call(String dockerCreds, String werfargs){
  // логин в registry
  // первый аргумент - url (пуст, т.к. используем DockerHub)
  // второй - имя Jenkins-секрета, где лежат доступы (login, password)
  docker.withRegistry("", "${dockerCreds}") {
    sh """#!/bin/bash -el
          set -o pipefail
          type trdl && source <(trdl use werf 1.2 stable)
          werf version
          werf ${werfargs}""".trim()
    }
}