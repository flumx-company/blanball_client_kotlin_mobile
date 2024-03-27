package com.example.blanball.utils.errorshandler

data class ErrorsMessages(
    val password_change: String = "Ваш пароль було успішно змінено",
    val phone_change: String = "Ваш номер телефону було успішно змінено",
    val email_change: String = "Ваш імейл було успішно змінено",
    val verified_change: String = "Ваш обліковий запис було успішно верифіковано",
    val account_deleted: String = "Ваш обліковий запис було успішно видалено",
    val password_reset: String = "Ваш пароль було успішно змінено",
    val sent_code_to_email: String = "Код підтвердження був успішно відправлений на ваш імейл",
    val account_already_verified: String = "Обліковий запис вже верифіковано",
    val invalid_configuration: String = "Перевірте правильність введених даних у полі \"Конфіденційність\"",
    val invalid_get_planed_events: String = "Неправильно введені дані у полі \"Конфедиційність - заплановані події\" - при зміні профілю",
    val age_over_80_years: String = "Наша система дає можливість вибрати вік не більше 80 років",
    val age_less_6_years: String = "Наша система дає можливість вибрати вік не менше 6 років",
    val wrong_old_password: String = "Перевірте правильність введеного поточногу паролю",
    val passwords_do_not_match: String = "Введені паролі не збігаються",
    val invalid_credentials: String = "Перевірте правильність введених даних",
    val not_verified: String = "Обліковий запис ще не верифікований",
    val no_permissions: String = "На жаль, у вас немає доступу до цієї дії"                                                                                ,
    val bad_verify_code: String = "Перевірте правильність введеного коду підтвердження",
    val verify_code_expired: String = "Час дії цього коду підтвердження закінчився",
    val email_already_in_use: String = "Введена пошта вже використовується іншим користувачем",
    val event_deleted: String = "Подія була успішно видалена",
    val event_updated: String = "Подія була успішно оновлена",
    val sent_request_to_participation: String = "Запит на участь був успішно надісланий автору події",
    val join_to_event: String = "Ви успішно підключилися до участі у цій події",
    val disconnect_from_event: String = "Ви більше не перебуваєте у списку учасників цієї події",
    val removed_user_from_event: String = "Kористувач був успішно вилучений з події",
    val sent_invite: String = "Запрошення на подію було успішно надіслано",
    val already_sent_request_to_participate: String = "Ви вже надіслали запит на участь у цій події",
    val author_cant_enter_his_event: String = "На жаль, але ви не можете стати учасником або глядачем своєї події",
    val bad_event_date_and_time: String = "Не валідна дата початку події, дата початку має бути більшою за поточну дату + 1 годину",
    val already_like_member: String = "Ви вже берете участь у цій події як гравець",
    val already_like_fan: String = "Ви вже берете участь у цій події як глядач",
    val not_in_event_fans_list: String = "Вас ще немає в списку глядачів цієї події",
    val not_in_event_members_list: String = "Вас ще немає в списку учасників цієї події",
    val author_cannot_be_invited_to_his_event: String = "На жаль, але ви не можете запрошувати користувачів на цю подію",
    val no_place: String = "На жаль, але на цій події більше немає місць для участі",
    val no_permissions_to_invite: String = "На жаль, але у вас немає прав запрошувати користувачів на цю подію",
    val this_user_cant_be_invited: String = "Цей користувач не може бути запрошений на цю подію",
    val price_description_required: String = "Якщо ви вказали ціну для події, то її обов'язково потрібно підкріпити описом",
    val cant_invite_yourself: String = "На жаль, але ви не можете запрошувати самого себе на подію",
    val nothing_found_for_request: String = "По вашому запиту з координатами нічого не було знайдено",
    val all_notifications_deleted: String = "Всі ваші нотифікації були успішно видалені",
    val all_notifications_readed: String = "Всі ваші нотифікації були успішно прочитані",
    val cant_leave_review_about_yourself: String = "Ви не можете залишити відгук про самого себе",
    val invalid_page: String = "Такої сторінки не існує",
    val not_found: String = "\"{field}\" - объект не был найден!",
    val required: String = "Поле \"{field}\" обов'язкове для заповнення",
    val does_not_exist: String = "\"{field}\" - не існує",
    val invalid_choice: String = "Поле \"{field}\" Выбрано с неправильным значением!",
    val unique: String = "Поле \"{field}\" має бути унікальним",
    val min_value: String = "Ви ввели у значення менше мінімального для поля \"{field}\"",
    val max_value: String = "Ви ввели у значення більше максимального для поля \"{field}\"",
    val blank: String = "Поле \"{field}\" не может быть пустым!",
    val authentication_credentials_were_not_provided: String = "Не предоставлены учетные данные для аутентификации",
    val authentication_token_not_valid: String = "Не валідний токен аутентифікації",
    val bad_authentication_token: String = "Не валідний токен аутентифікації",
    val avatar_max_size_1_mb: String = "Завантажене фото підвищує ліміт 1мб",
    val profile_avatar_updated: String = "Ваш аватар профілю був успішно оновлений",
    val code_is_valid: String = "Код для сбросу пароля валідний",
    val phone_invalid_phone_number: String = "Перевірте правильність введеного номера телефону",
    val method_not_allowed: String = "Метод http запиту заборонено",
    val fields_phone: String = "Телефон",
    val fields_email: String = "Почта",
    val fields_password: String = "Пароль",
    val fields_re_password: String = "Підтвердіть пароль",
    val fields_get_planned_events: String = "Заплановані події",
    val fields_configuration: String = "Конфіденційність",
    val fields_user: String = "Користувач",
    val fields_event: String = "Подія",
    val fields_notification: String = "Нотифікація",
    val fields_profile_place_place_name: String = "Адреса",
    val fields_profile_place_lat: String = "Адреса",
    val fields_profile_place_lon: String = "Адреса",
    val fields_profile_name: String = "Ім'я",
    val fields_profile_last_name: String = "Прізвище",
    val fields_profile_gender: String = "Гендер",
    val fields_profile_birthday: String = "Дата народження",
    val fields_profile_height: String = "Зріст",
    val fields_profile_weight: String = "Вага",
    val fields_profile_position: String = "Позиція на полі",
    val fields_profile_about_me: String = "Про мене",
    val fields_profile_working_leg: String = "Робоча нога",
)