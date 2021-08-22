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
        Field testPrimaryKey = TestClass.class.getDeclaredField("primaryKey");
        testPrimaryKey.setAccessible(true);
        Assert.assertEquals(testMetamodel.getPrimaryKeyField().getClassFieldName(), testPrimaryKey.getName());
    }

    @Test
    public void getColumn() throws NoSuchFieldException
    {
        Metamodel<TestClass> testMetamodel = new Metamodel<>(TestClass.class);
        Field testColumn = TestClass.class.getDeclaredField("column");
        testColumn.setAccessible(true);
        Assert.assertEquals(testMetamodel.getColumnFields().get(0).getClassFieldName(), testColumn.getName());
    }
}
