#### 主仓库：[DataWarehouse](https://github.com/zb2313/DataWarehouse)

### 数据仓库功能接口

| 函数名   | 参数                             | 返回                                                         |
| -------- | -------------------------------- | ------------------------------------------------------------ |
|          | 年 √                            | 电影名、asin、版本、风格、评分、导演名、演员名               |
|          | 月 √                          | 电影名、asin、版本、风格、评分、导演名、演员名               |
|          | 日 √                          | 电影名、asin、版本、风格、评分、导演名、演员名               |
|          | 年+月 √                         | 电影名、asin、版本、风格、评分、导演名、演员名               |
|          | 年+日 √                        | 电影名、asin、版本、风格、评分、导演名、演员名               |
|          | 日+月 √                        | 电影名、asin、版本、风格、评分、导演名、演员名               |
|          | 年+月+日 √                       | 电影名、asin、版本、风格、评分、导演名、演员名               |
|          | 季度 √                       | 电影名、asin、版本、风格、评分、导演名、演员名               |
|          | 年+季度 √                     | 电影名、asin、版本、风格、评分、导演名、演员名               |
|          | 星期几 √                     | 电影名、asin、版本、风格、评分、导演名、演员名               |
|          | 年+星期几 x                  | 电影名、asin、版本、风格、评分、导演名、演员名               |
|          | 月+星期几 x                    | 电影名、asin、版本、风格、评分、导演名、演员名               |
| 全字匹配 | 电影名称 √                      | 电影名、asin、版本、风格、评分、导演名、演员名、版本数目(来自于版本如DVD，蓝光) |
| 模糊匹配 | 电影名称 √                      | 电影名、asin、版本、风格、评分、导演名、演员名               |
|         | asin√                           | 电影完整信息                                       |
|           |asin√                           | 图片链接                                               |
|          | 导演id √                         | 电影名                                                       |
| 模糊查询 | 导演name √                         | 电影名 -> 匹配的导演名                                                      |
|          | 演员id √                          | 电影名                                                       |
| 模糊查询 | 演员name √                         | 电影名 -> 匹配的演员名                                                     |
|          | 导演id √                          | 10条内容的列表（导演id 导演名字、合作次数） |
|          | 导演id √                            | 10条内容的列表（演员id 演员名字、合作次数） |
|          | 演员id √                           | 10条内容的列表（导演id 导演名字、合作次数） |
|          | 演员id √                            | 10条内容的列表（演员id 演员名字、合作次数） |
|          | 没有输入（经常合作的导演和演员）√ **neo4j对比** | 100条内容的列表（导演、演员、合作次数）                      |
|          | 没有输入（经常合作的导演和导演）√**neo4j对比** | 100条内容的列表（导演、导演、合作次数）                      |
|          | 没有输入（经常合作的演员和演员）√**neo4j对比** | 100条内容的列表（演员、演员、合作次数）                      |
|          | 导演id + 演员id√                       |合作过的电影asin和平均分             |
|          | 导演id + 导演id√                        |合作过的电影asin和平均分             |
|          | 演员id + 演员id√                         |合作过的电影asin和平均分              |
| imdb榜单 | 无√                              |返回前100或前50的电影 看你速度                                       |
|          | 精确类别stylename√                             | 电影数量                                                     |
|          | 无输入√                             | 100条类别名和电影数量                                                     |
|          | **上面两条组合使用（先通过下面的画出饼状图 再通过上面的具体查询数量）**                             |                                                     |
|          | 分数（double）√                    |    （大于等于这个分数的电影数量）  |
|          | 有帮助的人数的数量 √                   | 大于这个有帮助人数的电影数量 |
|          | **上面使用饼状图**                   |            |
|          | 年+导演id √                          | asin |
|          | 年+演员id  √                         | asin |
|          | 导演id+类别  √                      | asin |
|          | 演员id+类别  √                      | asin |
|          | ~~年+导演+类别~~                   |                                                              |
|          | ~~年+演员+类别~~                   ||
|          | 年份 + 类别√                             |该年份的该类别数量|
|          | 类别√                              |reviewpoint平均分 imdb平均分 |
|          | 类别√                              |情感分数的平均分 |
|          | **上面使用折线图或者柱状图**                |情感分数的平均分，imdb平均分 用户评价平均分|
| 溯源查询 | 无输入√                 | 总的数据条数 去除重复asin后的数据条数 电影条数       |
|          | asin√ | 该电影的系列的信息 |
|          | seriesid（seriesid通过上一条接口获得）√ | 该系列的电影列表简略信息 |
|          |                                  |                                                              |
|          | √：表示接口已完成 ||