
### Задание 1. Практика тестирования

Задача - Покрыть код предлагаемого проекта тестами
Код взять отсюда: https://github.com/idontgiveit/otus-unittests-2  

#### Что сделано
- gradle заменен на maven
- добавлен плагин jacoco-maven-plugin для генерации отчета по покрытию.
- Добвлены тесты

#### Запуск

```
git clone https://github.com/filatkinen/javaprofmodules
cd javaprofmodules
git switch mod01_testing
cd mod01_testing

mvn verify
```

#### Проверка покрытия

```
cd target/site/jacoco

#Открыть браузером index.html

```

