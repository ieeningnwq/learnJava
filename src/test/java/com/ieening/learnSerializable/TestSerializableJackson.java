package com.ieening.learnSerializable;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ieening.learnSerializable.shape.Circle;
import com.ieening.learnSerializable.shape.CircleWithTypeInfo;
import com.ieening.learnSerializable.shape.Shape;
import com.ieening.learnSerializable.shape.ShapeManager;
import com.ieening.learnSerializable.shape.ShapeManagerWithTypeInfo;
import com.ieening.learnSerializable.shape.ShapeWithTypeInfo;
import com.ieening.learnSerializable.shape.Square;
import com.ieening.learnSerializable.shape.SquareWithTypeInfo;

public class TestSerializableJackson {
    String studentJsonFileName = "src\\main\\resources\\serializable\\studentJackson.json";

    @Test
    public void testStudentJackson() throws JsonProcessingException {
        Student student = new Student("钱七", 20, 90d);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String studentString = mapper.writeValueAsString(student);
        studentString = studentString.replaceAll("\\s", "");
        assertEquals("{\"name\":\"钱七\",\"age\":20,\"score\":90.0}", studentString);
    }

    @Test
    public void testStudentJacksonWriterReader() throws IOException {
        File file = new File(studentJsonFileName);
        if (file.exists()) {
            file.delete();
        }

        Student student = new Student("钱七", 20, 90d);
        // 写入
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try (PrintWriter pWriter = new PrintWriter(studentJsonFileName)) {
            mapper.writeValue(pWriter, student);
        }
        // 读取
        Student s = mapper.readValue(new File(studentJsonFileName), Student.class);
        // 断言
        assertEquals(student, s);
    }

    String studentXmlFileName = "src\\main\\resources\\serializable\\studentJackson.xml";

    @Test
    public void testStudentJacksonXmlWriterReader() throws IOException {
        File file = new File(studentXmlFileName);
        if (file.exists()) {
            file.delete();
        }

        Student student = new Student("钱七", 20, 90d);
        // 转换为字符串
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String studentString = mapper.writeValueAsString(student).replaceAll("\\s", "");
        assertEquals("<Student><name>钱七</name><age>20</age><score>90.0</score></Student>", studentString);
        // 写入
        try (PrintWriter pWriter = new PrintWriter(studentXmlFileName)) {
            mapper.writeValue(pWriter, student);
        }
        // 读取
        Student s = mapper.readValue(new File(studentXmlFileName), Student.class);
        // 断言
        assertEquals(student, s);
    }

    String studentMessagePackFileName = "src\\main\\resources\\serializable\\studentJackson.bson";

    @Test
    public void testStudentJacksonMessagePackWriterReader() throws IOException {
        File file = new File(studentMessagePackFileName);
        if (file.exists()) {
            file.delete();
        }

        Student student = new Student("钱七", 20, 90d);
        // 转换为字节数组
        ObjectMapper mapper = new ObjectMapper(new MessagePackFactory());
        byte[] studentBytes = mapper.writeValueAsBytes(student);
        assertArrayEquals(new byte[] { -125, -92, 110, 97, 109, 101, -90, -23, -110, -79, -28, -72, -125, -93, 97, 103,
                101, 20, -91, 115, 99, 111, 114, 101, -53, 64, 86, -128, 0, 0, 0, 0, 0 }, studentBytes);
        // 写入
        mapper.writeValue(new File(studentMessagePackFileName), student);
        // 读取
        Student s = mapper.readValue(new File(studentMessagePackFileName), Student.class);
        // 断言
        assertEquals(student, s);
    }

    List<Student> students = Arrays
            .asList(new Student[] { new Student("张三", 18, 80.9d), new Student("李四", 17, 67.5d),
                    new Student("王五", 17, 67.5d), new Student("赵六", 17, 67.5d) });
    String studentListFileName = "src\\main\\resources\\serializable\\studentJacksonList.json";

    @Test
    public void testStudentJacksonListWriterReader() throws IOException {
        File file = new File(studentListFileName);
        if (file.exists()) {
            file.delete();
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String studentString = mapper.writeValueAsString(students).replaceAll("\\s", "");
        assertEquals(
                "[{\"name\":\"张三\",\"age\":18,\"score\":80.9},{\"name\":\"李四\",\"age\":17,\"score\":67.5},{\"name\":\"王五\",\"age\":17,\"score\":67.5},{\"name\":\"赵六\",\"age\":17,\"score\":67.5}]",
                studentString);
        // 写入
        mapper.writeValue(new File(studentListFileName), students);
        // 读取
        List<Student> studentList = mapper.readValue(new File(studentListFileName), new TypeReference<List<Student>>() {
        });
        // 断言
        assertArrayEquals(students.toArray(), studentList.toArray());
    }

    String studentMapFileName = "src\\main\\resources\\serializable\\studentJacksonMap.xml";

    @Test
    public void testStudentJacksonMapWriterReader() throws IOException {
        File file = new File(studentMapFileName);
        if (file.exists()) {
            file.delete();
        }
        Map<String, Student> studentsMap = new HashMap<String, Student>();
        studentsMap.put("zhangsan", new Student("张三", 18, 80.9d));
        studentsMap.put("lisi", new Student("李四", 17, 67.5d));
        studentsMap.put("wangwu", new Student("王五", 17, 67.5d));
        studentsMap.put("zhaoliu", new Student("赵六", 17, 67.5d));
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String studentString = mapper.writeValueAsString(studentsMap).replaceAll("\\s", "");
        assertEquals(
                "<HashMap><lisi><name>李四</name><age>17</age><score>67.5</score></lisi><zhaoliu><name>赵六</name><age>17</age><score>67.5</score></zhaoliu><zhangsan><name>张三</name><age>18</age><score>80.9</score></zhangsan><wangwu><name>王五</name><age>17</age><score>67.5</score></wangwu></HashMap>",
                studentString);
        // 写入
        mapper.writeValue(new File(studentMapFileName), studentsMap);
        // 读取
        Map<String, Student> map = mapper.readValue(new File(studentMapFileName),
                new TypeReference<HashMap<String, Student>>() {

                });
        // 断言
        assertEquals(
                "{lisi=Student [name=李四, age=17, score=67.5], zhaoliu=Student [name=赵六, age=17, score=67.5], zhangsan=Student [name=张三, age=18, score=80.9], wangwu=Student [name=王五, age=17, score=67.5]}",
                map.toString());
    }

    @Test
    public void testStudentJacksonPropertyIgnore() throws JsonProcessingException {
        StudentJacksonPropertyIgnore student = new StudentJacksonPropertyIgnore("钱七", 20, 90d);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String studentString = mapper.writeValueAsString(student);
        studentString = studentString.replaceAll("\\s", "");
        assertEquals("{\"name\":\"钱七\"}", studentString);
    }

    String aCommonJsonFilePath = "src\\main\\resources\\serializable\\aCommonJsonCommon.json";

    @Test
    public void testJacksonIdentity() throws IOException {
        Common common = new Common();
        common.setName("common");
        JsonIdentityCommon jCommon = new JsonIdentityCommon();
        jCommon.setName("JsonIdentityCommon");

        A a = new A();
        a.firstCommon = a.secondCommon = common;
        a.firstJsonIdentityCommon = a.secondJsonIdentityCommon = jCommon;

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(aCommonJsonFilePath), a);

        A aValue = mapper.readValue(new File(aCommonJsonFilePath), A.class);
        assertTrue(a.firstCommon == a.secondCommon);
        assertTrue(a.firstJsonIdentityCommon == a.secondJsonIdentityCommon);
        assertFalse(aValue.firstCommon == aValue.secondCommon);
        assertTrue(aValue.firstJsonIdentityCommon == aValue.secondJsonIdentityCommon);
    }

    @Test
    public void testComplexStudent() throws StreamWriteException, DatabindException, IOException {
        Map<String, Double> scoreMap = new HashMap<>();
        scoreMap.put("语文", 89d);
        scoreMap.put("数学", 83d);
        scoreMap.put("英语", 110d);

        ComplexStudent.ContactInfo contactInfo = new ComplexStudent.ContactInfo("18500308990", "zhangsan@sina.com",
                "少林寺");

        ComplexStudent cStudent = new ComplexStudent("张三丰", 16, scoreMap, contactInfo);

        String complexStudentInfoJsonFileName = "src\\\\main\\\\resources\\\\serializable\\\\complexStudentInfo.json";
        // 序列化
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.enable(SerializationFeature.INDENT_OUTPUT);
        oMapper.writeValue(new File(complexStudentInfoJsonFileName), cStudent);

        // 反序列化
        ComplexStudent sComplexStudent = oMapper.readValue(new File(complexStudentInfoJsonFileName),
                ComplexStudent.class);
        assertEquals(cStudent, sComplexStudent);
    }

    @Test(expected = JsonMappingException.class)
    public void testJacksonCircularReferences() throws StreamWriteException, DatabindException, IOException {

        CircularReferencesA a = new CircularReferencesA();
        a.setName("A");
        CircularReferencesB b = new CircularReferencesB();
        b.setName("B");
        a.setB(b);
        b.setA(a);

        // 序列化
        String circularReferencesAJsonFileName = "src\\\\main\\\\resources\\\\serializable\\\\circularReferencesA.json";
        String circularReferencesBJsonFileName = "src\\\\main\\\\resources\\\\serializable\\\\circularReferencesB.json";
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.enable(SerializationFeature.INDENT_OUTPUT);
        oMapper.writeValue(new File(circularReferencesAJsonFileName), a);
        oMapper.writeValue(new File(circularReferencesBJsonFileName), b);

    }

    @Test
    public void testJacksonCircularReferencesWithAnnotation()
            throws StreamWriteException, DatabindException, IOException {

        CircularReferencesAWithAnnotation a = new CircularReferencesAWithAnnotation();
        a.setName("A");
        CircularReferencesBWithAnnotation b = new CircularReferencesBWithAnnotation();
        b.setName("B");
        a.setB(b);
        b.setA(a);

        // 序列化
        String circularReferencesAJsonFileName = "src\\\\main\\\\resources\\\\serializable\\\\circularReferencesAWithAnnotation.json";
        String circularReferencesBJsonFileName = "src\\\\main\\\\resources\\\\serializable\\\\circularReferencesBWithAnnotation.json";
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.enable(SerializationFeature.INDENT_OUTPUT);
        oMapper.writeValue(new File(circularReferencesAJsonFileName), a);
        oMapper.writeValue(new File(circularReferencesBJsonFileName), b);

        // 反序列化
        CircularReferencesAWithAnnotation sA = oMapper.readValue(new File(circularReferencesAJsonFileName),
                CircularReferencesAWithAnnotation.class);
        CircularReferencesBWithAnnotation sB = oMapper.readValue(new File(circularReferencesBJsonFileName),
                CircularReferencesBWithAnnotation.class);
        assertEquals(null, sB.getA());
        assertEquals(b.getName(), sA.getB().getName());
        assertEquals(a.getName(), sA.getB().getA().getName());
    }

    @Test(expected = UnrecognizedPropertyException.class)
    public void testJacksonAddField()
            throws StreamWriteException, DatabindException, IOException {
        String addFieldJsonFileName = "src\\main\\resources\\serializable\\addFieldStudent.json";
        ObjectMapper mapper = new ObjectMapper();
        Student s = mapper.readValue(new File(addFieldJsonFileName), Student.class);
    }

    @Test
    public void testJacksonAddFieldWithProperty()
            throws StreamWriteException, DatabindException, IOException {
        String addFieldJsonFileName = "src\\main\\resources\\serializable\\addFieldStudent.json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Student s = mapper.readValue(new File(addFieldJsonFileName), Student.class);
        assertEquals("Student [name=张三, age=18, score=333.0]", s.toString());
    }

    @Test(expected = UnrecognizedPropertyException.class)
    public void testJacksonShapeManager() throws StreamWriteException, DatabindException, IOException {
        ShapeManager sManager = new ShapeManager();
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(10));
        shapes.add(new Square(12));
        sManager.setShapes(shapes);
        // 序列化
        String shapeManagerJsonFileName = "src\\main\\resources\\serializable\\shapeManager.json";
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.enable(SerializationFeature.INDENT_OUTPUT);
        oMapper.writeValue(new File(shapeManagerJsonFileName), sManager);
        // 反序列化
        oMapper.readValue(new File(shapeManagerJsonFileName), ShapeManager.class);
    }

    @Test
    public void testJacksonShapeManagerWithTypeInfo() throws StreamWriteException, DatabindException, IOException {
        ShapeManagerWithTypeInfo sManager = new ShapeManagerWithTypeInfo();
        List<ShapeWithTypeInfo> shapes = new ArrayList<>();
        shapes.add(new CircleWithTypeInfo(10));
        shapes.add(new SquareWithTypeInfo(12));
        sManager.setShapes(shapes);
        // 序列化
        String shapeManagerJsonFileName = "src\\main\\resources\\serializable\\shapeManagerWithTypeInfo.json";
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.enable(SerializationFeature.INDENT_OUTPUT);
        oMapper.writeValue(new File(shapeManagerJsonFileName), sManager);
        // 反序列化
        oMapper.readValue(new File(shapeManagerJsonFileName), ShapeManagerWithTypeInfo.class);
    }

    @Test
    public void testJacksonStudentWithJsonProperty() throws StreamWriteException, DatabindException, IOException {
        StudentWithJsonProperty student = new StudentWithJsonProperty("朱一", 6, 7);
        // 序列化
        String jsonFileName = "src\\main\\resources\\serializable\\studentWithJsonProperty.json";
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.enable(SerializationFeature.INDENT_OUTPUT);
        oMapper.writeValue(new File(jsonFileName), student);
        // 反序列化
        StudentWithJsonProperty s = oMapper.readValue(new File(jsonFileName), StudentWithJsonProperty.class);
        // 断言
        assertEquals("Student [name=朱一, age=6, score=7.0]", s.toString());
    }

    @Test
    public void testJacksonTwoDate() throws StreamWriteException, DatabindException, IOException {
        Date dateNowFirst = Date.from(Instant.now());
        Date dateNowSecond = Date.from(Instant.now());
        TwoDate tDate = new TwoDate();
        tDate.setFirst(dateNowFirst);
        tDate.setSecond(dateNowSecond);

        // 序列化
        String jsonFileName = "src\\main\\resources\\serializable\\twoDate.json";
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.enable(SerializationFeature.INDENT_OUTPUT);
        oMapper.writeValue(new File(jsonFileName), tDate);
    }

    @Test(expected = InvalidDefinitionException.class)
    public void testJacksonStudentWithoutDefaultConstructor()
            throws StreamWriteException, DatabindException, IOException {
        StudentWithoutDefaultConstructor student = new StudentWithoutDefaultConstructor("谢二", 0, 0);

        // 序列化
        String jsonFileName = "src\\main\\resources\\serializable\\studentWithoutDefaultConstructor.json";
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.enable(SerializationFeature.INDENT_OUTPUT);
        oMapper.writeValue(new File(jsonFileName), student);
        // 反序列化
        oMapper.readValue(new File(jsonFileName), StudentWithoutDefaultConstructor.class);
    }

    @Test
    public void testJacksonStudentWithoutDefaultConstructorWithJsonCreator()
            throws StreamWriteException, DatabindException, IOException {
        StudentWithoutDefaultConstructorWithJsonCreator student = new StudentWithoutDefaultConstructorWithJsonCreator(
                "谢二", 0, 0);

        // 序列化
        String jsonFileName = "src\\main\\resources\\serializable\\studentWithoutDefaultConstructorWithJsonCreator.json";
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.enable(SerializationFeature.INDENT_OUTPUT);
        oMapper.writeValue(new File(jsonFileName), student);
        // 反序列化
        oMapper.readValue(new File(jsonFileName), StudentWithoutDefaultConstructorWithJsonCreator.class);
    }

    @Test(expected = MismatchedInputException.class)
    public void testJacksonXmlLimitations() throws StreamWriteException, DatabindException, IOException {
        Map<String, List<String>> map = new HashMap<>();
        map.put("hello", Arrays.asList(new String[] { "你好", "吃了吗", "吃饭了" }));

        // 序列化
        String jsonFileName = "src\\main\\resources\\serializable\\xmlLimitations.json";
        ObjectMapper oMapper = new XmlMapper();
        oMapper.enable(SerializationFeature.INDENT_OUTPUT);
        oMapper.writeValue(new File(jsonFileName), map);
        // 反序列化
        oMapper.readValue(new File(jsonFileName), new TypeReference<Map<String, List<String>>>() {

        });
    }
}
