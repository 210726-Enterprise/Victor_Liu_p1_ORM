import com.revature.orm.model.ColumnField;
import com.revature.orm.model.Metamodel;
import com.revature.orm.model.PrimaryKeyField;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MetamodelTest
{
    @Test
    public void getAClassTest()
    {
        Metamodel<String> stringMetamodel = new Metamodel<>(String.class);
        Assert.assertEquals(stringMetamodel.getAClass(), String.class);
    }

    @Test
    public void getPrimaryKeyFieldTest() throws NoSuchFieldException
    {
        Metamodel<TestClass> testMetamodel = new Metamodel<>(TestClass.class);
        Field testField = TestClass.class.getField("primaryKey");
        testField.setAccessible(true);
        PrimaryKeyField testPrimaryKey = new PrimaryKeyField(testField);
        Assert.assertEquals(testMetamodel.getPrimaryKeyField(), testPrimaryKey);
    }

    @Test
    public void getColumn() throws NoSuchFieldException
    {
        Metamodel<TestClass> testMetamodel = new Metamodel<>(TestClass.class);
        List<ColumnField> testColumnFields = new ArrayList<>();
        Field testField = TestClass.class.getField("column");
        testField.setAccessible(true);
        ColumnField testColumn = new ColumnField(testField);
        testColumnFields.add(testColumn);
        Assert.assertEquals(testMetamodel.getColumnFields(), testColumnFields);
    }
}
