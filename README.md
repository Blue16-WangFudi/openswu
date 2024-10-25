# OpenSWU

[![License](https://img.shields.io/badge/License-AGPL%203.0-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)
[![Java Version](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/technologies/javase-jdk21-relnotes.html)

## 项目简介

OpenSWU 是一个 Maven 依赖库，用于对接西南大学教务系统的相关 API 接口，从而实现对学生相关信息的查询。本项目整合了两种主流的查询方式，旨在为开发者提供便捷的接口访问手段。

## 安装指南

首先，你需要下载本项目并执行 Maven 安装命令：

```bash
mvn install
```

接着，在你的 Maven 项目中添加如下依赖：

```xml
<!-- OpenSWU -->
<dependency>
    <groupId>cn.blue16</groupId>
    <artifactId>openswu</artifactId>
    <version>0.1.0-alpha</version>
</dependency>
```

## 环境要求

- Java Development Kit (JDK) 版本：21

## 使用方法

### 示例代码

```java
public interface SwuInterface {
    LoginResult login(String username, String password) throws LoginFailedException, ConnectionException;
    LoginResult getBaseInfo(String username) throws LoginExpiredException;
    ScheduleResult getSchedule(String username) throws LoginExpiredException;
}
```

### 步骤说明

1. 调用 `login` 方法进行登录认证。
2. 登录成功后，可以调用 `getBaseInfo` 获取学生的基本信息。
3. 调用 `getSchedule` 获取学生的课程表信息。

## 开源协议

本项目遵循 GNU Affero General Public License v3.0 (AGPL) 协议。

## 支持与贡献

如果你发现任何 bug 或者有新的特性建议，请通过 [issue tracker](<ISSUE_TRACKER_LINK>) 提交问题。

如果你希望为本项目贡献代码，请先阅读 [CONTRIBUTING.md] 文件。

## 联系我们

如有任何疑问或建议，请通过以下方式联系我们：
- 邮箱: blue16@email.swu.edu.cn
- GitHub: Blue16-WangFudi
