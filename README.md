# DD-SortFile

Задание решено 2-мя алгоритмами.

1. Стандартный алгоритм слиянием.
2. qSort с использованием буффера в оперативной пями и разбиением на несколько маленьких частей.

Оба алгоритма тестировались на файле в 100 000 000 строк, размер каждой строки до 1000 символов. Общий размер файла составил ~4Gb.
Стандартный алгоритм слиянием отработал за 36 минут, алгоритм с использованием буффера за 5 минут. Дополнительные опции при запуске JAVA VM - -Xms512m -Xmx512m (512 мб оперативной памяти).
