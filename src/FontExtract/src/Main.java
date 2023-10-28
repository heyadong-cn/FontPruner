import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		int argLength = args.length;
		if(argLength!=2){
			System.out.println("�����������[0]��Ҫ��ȡ���ı�·���б�   [1]���·��");
			System.exit(0);
		}

		String fileListString =args[0];
		File file = new File(fileListString);
		if(!file.exists()){
			System.out.println("�ı�·���б�����"+file.toString());
			System.exit(1);
		}
		if(!file.isFile()){
			System.out.println("�ı�·���б����ļ�"+file.toString());
			System.exit(1);
		}
		
		
		StringBuffer strBuf = new StringBuffer();
		List<String> filePathList =ExtractionOperationUtil.ExtractStringListFromFile(file);
		for(String filePath :filePathList)
		{
			File f = new File(filePath);	
			if(!f.exists() ||!f.isFile()){
				System.out.println("�ı������ڻ�����Ч"+f.toString());
				continue;
			}
			strBuf.append(ExtractionOperationUtil.ExtractStringFromFile(f));
		}
		
		String outputPath =args[1];
		File outputFile = new File(outputPath);
		if(!outputFile.exists()){
			outputFile.mkdirs();
		}
		if(outputFile.isFile()){
			System.out.println("·�����ļ�"+outputFile.toString());
			System.exit(1);
		}
		ExtractResult result =ExtractionOperationUtil.ExtractUnrepeatedWordsFromString(strBuf.toString());
		ExtractionOperationUtil.WriteStr2File(result.chineseCharString,new File(outputPath+"\\ChineseOutPut.txt"),"UTF-8");
		ExtractionOperationUtil.WriteStr2File(result.unChineseCharString,new File(outputPath+"\\unChineseOutPut.txt"),"UTF-8");
		
		System.out.println("��ȡ���");
	}
}



