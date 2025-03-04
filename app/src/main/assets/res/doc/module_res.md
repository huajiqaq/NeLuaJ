# NeLuaJ+ 的 `res` 模块介绍

`NeLuaJ+` 对 `LuaJ++` 的 `res` 模块进行了增强，特别是对多语言和资源管理的支持进行了优化。

---

## 功能概览

### **1. res.string**
- **主要功能**: 读取和管理字符串资源。
- **实现逻辑**:
    1. 从 `init.lua` 文件中读取信息。
    2. 检查是否存在与当前设备语言对应的资源文件。
    3. 如果存在对应语言文件，加载该文件。
    4. 如果不存在对应语言文件，则加载默认语言配置（存储在 `default.lua` 中）。

---

### **2. res.bitmap / res.drawable**
- **主要功能**:
    - 提供对 `drawable` 文件夹中图片资源的访问。
- **支持文件类型**:
    - `bmp`、`jpeg`、`jpg`、`png`、`gif`、`svg`、`webp`、`heif`、`heic`、`avif`
- **返回值**:
    - 图片的 `Bitmap` 对象或 `Drawable` 对象。

---

### **3. res.font**
- **主要功能**: 处理字体资源。
- **返回值**:
    - `font` 文件夹中的 `ttf` 或 `otf` 字体文件对应的 `Typeface` 对象。

---

### **4. res.layout**
- **主要功能**: 获取布局文件。
- **返回值**:
    - `layout` 文件夹中的布局表。

---

### **5. res.view**
- **主要功能**: 获取加载后的布局。
- **返回值**:
    - `layout` 文件夹中加载后的布局视图对象。

---

### **6. res.dimen**
- **主要功能**: 读取尺寸信息。
- **实现逻辑**:
    1. 从 `init.lua` 文件中读取配置信息。
    2. 根据屏幕方向加载相应的文件：
        - 横屏加载 `land.lua`。
        - 竖屏加载 `port.lua`。
        - 未定义方向时加载 `undefined.lua`。

---

### **7. res.color**
- **主要功能**: 处理颜色资源。
- **实现逻辑**:
    1. 从 `init.lua` 文件中读取配置信息。
    2. 根据系统模式加载颜色文件：
        - 白天模式加载 `day.lua`。
        - 夜间模式加载 `night.lua`。

---

### **8. res.language**
- **主要功能**: 获取当前使用的语言。
- **返回值**:
    - `string` `res.string` 中定义的当前语言。

---