# Console-CRUD-Application

Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:


User (id, firstName, lastName, List<Post> posts, Region region)
Post (id, content, created, updated)
Region (id, name)
Role (enum ADMIN, MODERATOR, USER)


В качестве хранилища данных необходимо использовать текстовые файлы:
users.txt, posts.txt, regions.txt

Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.

Слои:
model - POJO класы
repository - классы, реализующие доступ к текстовым файлам
controller - обработка запросов от пользователя
view - все данные, необходимые для работы с консолью

Например: User, UserRepository, UserController, UserView и т.д.


Для репозиторного слоя желательно использовать базовый интерфейс:
interface GenericRepository<T,ID>

interface UserRepository extends GenericRepository<User, Long>

class JavaIOUserRepositoryImpl implements UserRepository

Результатом выполнения задания должен быть отдельный репозиторий с README.md файлом,
который содержит описание задачи, проекта и инструкции запуска приложения через командную строку.
