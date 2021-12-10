### 访问[http://139.196.202.57:8089/swagger-ui/](http://139.196.202.57:8089/swagger-ui/) 来产看接口文档

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
|  | asin                     | 电影完整信息               |
|          | 导演id √                         | 电影名                                                       |
| 模糊查询 | 导演name √                         | 电影名 -> 匹配的导演名                                                      |
|          | 演员id √                          | 电影名                                                       |
| 模糊查询 | 演员name √                         | 电影名 -> 匹配的演员名                                                     |
|          | 导演id √                          | 10条内容的列表（导演id 导演名字、合作次数） |
|          | 导演id √                            | 10条内容的列表（演员id 演员名字、合作次数） |
|          | 演员id  √                           | 10条内容的列表（导演id 导演名字、合作次数） |
|          | 演员id √                            | 10条内容的列表（演员id 演员名字、合作次数） |
|          | 没有输入（经常合作的导演和演员）√ | 100条内容的列表（导演、演员、合作次数）                      |
|          | 没有输入（经常合作的导演和导演）√ | 100条内容的列表（导演、导演、合作次数）                      |
|          | 没有输入（经常合作的演员和演员）√ | 100条内容的列表（演员、演员、合作次数）                      |
|          | 导演                             | 10条内容的列表（演员名字、演过所有电影的平均分）             |
|          | 导演                             | 10条内容的列表（导演名字、演过所有电影的平均分）             |
|          | 演员                             | 10条内容的列表（演员名字、演过所有电影的平均分）             |
|          | 演员                             | 10条内容的列表（导演名字、演过所有电影的平均分）             |
|          | 类别                             | 电影数量                                                     |
|          | 分数（double）                   | 100条（电影名、asin、版本、风格、评分、导演名、演员名）      |
|          | 没有输入（有帮助人数的电影）     | 100条内容的电影列表（有帮助人数的数量、电影名、asin、版本、风格、评分、导演名、演员名） |
|          | 年+导演                          |                                                              |
|          | 年+演员                          |                                                              |
|          | 导演+类别                        |                                                              |
|          | 演员+类别                        |                                                              |
|          | 年+导演+类别                     |                                                              |
|          | 年+演员+类别                     |                                                              |
| 溯源查询 | 电影确定名称                     | 电影系列名、该系列所有电影                                   |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
|          |                                  |                                                              |
