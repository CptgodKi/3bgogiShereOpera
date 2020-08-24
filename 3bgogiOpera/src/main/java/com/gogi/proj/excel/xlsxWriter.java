package com.gogi.proj.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gogi.proj.classification.code.vo.ClassificationVO;
import com.gogi.proj.classification.code.vo.ExcelOrderSeqVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOGetterSetting;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.stock.vo.PrintDataSetVO;

public class xlsxWriter {
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	private CellsStyle cs = new CellsStyle();
	
	private OrdersVOGetterSetting osGs;
	
	 public File orderXlsWriter(List<String> idxTitle, List<OrdersVO> veList, List<OrdersVOList> orderVoList  ,List<ExcelOrderSeqVO> eoSeq, String upPath, String fileName) {
		 	System.out.println("들어옴");
		 	osGs = new OrdersVOGetterSetting();
		 	
	        // 워크북 생성
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        
	        CellStyle style = workbook.createCellStyle();
	        // 워크시트 생성
	        HSSFSheet sheet = workbook.createSheet();
	        // 행 생성
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)500);
	        // 쎌 생성
	        HSSFCell cell;
	        
	        // 헤더 정보 구성
	        for(int i = 0; i < idxTitle.size(); i++) {
	        	cell = row.createCell(i);
	        	cell.setCellValue(idxTitle.get(i));
	        	cs.setCellsStylesSpecial(style, sheet, workbook, cell);
	        	
	        }
	        
	        // 리스트의 size 만큼 row를 생성
	        
	        int rowCounting = 1;
	        boolean productEx = false;
	        
	        for(int rowIdx=1 + 0; rowIdx < 1 + eoSeq.size(); rowIdx++) {
	        	
	        	row = sheet.createRow(rowCounting);
	        	row.setHeight((short)500);
	        	cell = row.createCell(0);
	        	cell.setCellValue(osGs.excelIOOrdersToeosSeq(eoSeq.get(rowIdx-1)).get(1));
	        	cs.setCellsStyles(style, sheet, workbook, cell);
        		cell = row.createCell(1);
        		cs.setCellsStyles(style, sheet, workbook, cell);
        		cell = row.createCell(2);
        		cs.setCellsStyles(style, sheet, workbook, cell);
        		sheet.addMergedRegion(new CellRangeAddress(rowCounting, rowCounting, 0, 2));
        		
	        	rowCounting++;
	        	
	        	if(rowIdx < eoSeq.size() + 1) {
	        		
	        		for(int i = 0; i < veList.size(); i++) {
	        			
	        			if(eoSeq != null) {
	        				
	        				//분류 코드에 따라 정렬
	        				if( eoSeq.get(rowIdx - 1).getEosSeq() == Integer.parseInt(veList.get(i).getOrUserColumn3())) {
	        					row = sheet.createRow(rowCounting);
	        					row.setHeight((short)500);
	        					//상품 정보, 분류코드,상품명옵션,개수
	        					List<String> osGsList = osGs.excelIOOrders(veList.get(i));
	        					
	        					//행 정보 생성
	        					for(int cellIdx = 0; cellIdx < osGsList.size(); cellIdx ++) {
	        						if(cellIdx == 0) {
	        							cell = row.createCell(cellIdx);
	        							if(osGsList.get(3) != null && !osGsList.get(3).equals("")) {
	        								cell.setCellValue(osGsList.get(1)+"\n"+osGsList.get(3));
	        								cs.setCellsStyles(style, sheet, workbook, cell);
	        								
	        							}else {
	        								cell.setCellValue(osGsList.get(1));
	        								cs.setCellsStylesSpecial(style, sheet, workbook, cell);
	        							}
	        							sheet.addMergedRegion(new CellRangeAddress(rowCounting, rowCounting, 0, 1));
	        							
	        						}else if(cellIdx == 1){
	        							cell = row.createCell(cellIdx);
	        							cs.setCellsStylesSpecial(style, sheet, workbook, cell);
	        						}else if(cellIdx == 2){	        							
	        							cell = row.createCell(cellIdx);
	        							cell.setCellValue(Integer.parseInt(osGsList.get(cellIdx)));
	        							cs.setCellsStylesSpecial(style, sheet, workbook, cell);
	        						}
	        						
	        					}//for
	        					
	        					rowCounting++;
	        				}//if
	        			}else {
	        				
	        				//행 정보 생성
        					for(int cellIdx = 0; cellIdx < veList.size(); cellIdx ++) {
        						cell = row.createCell(cellIdx);
        						cell.setCellValue(veList.get(cellIdx)+"");
        						cs.setCellsStylesSpecial(style, sheet, workbook, cell);
        						
        					}//for
        					
        					rowCounting++;
	        			}
	        			
	        		}//for
	        		
	        	}//if
	           
	        }//for
	        
	        if(orderVoList.size() != 0) {
	        	
				row = sheet.createRow(rowCounting);
	        	row.setHeight((short)500);
	        	cell = row.createCell(0);
	        	cell.setCellValue("이름");
	        	cs.setCellsStyles(style, sheet, workbook, cell);
        		cell = row.createCell(1);
        		cell.setCellValue("상품");
        		cs.setCellsStyles(style, sheet, workbook, cell);
        		cell = row.createCell(2);
        		cell.setCellValue("개수");
        		cs.setCellsStyles(style, sheet, workbook, cell);
        		
        		int vecRowCounting = rowCounting+1;
        		
        		List<Region> regList = new ArrayList<Region>();
        		
        		List<CellRangeAddress> cellList = new ArrayList<CellRangeAddress>();
        		
        		for(int veC = 0; veC < orderVoList.size(); veC++) {
        			
        			for(int q = 0; q < orderVoList.get(veC).getOrVoList().size(); q++) {
        				
        				row = sheet.createRow(vecRowCounting);

        				//첫번째 로우 시작
        				if(q == 0 ) {
        					//sheet.addMergedRegion(new Region(vecRowCounting, (short)vecRowCounting,  (vecRowCounting+ orderVoList.get(veC).getOrVoList().size()), (short)vecRowCounting ));
        					//sheet.addMergedRegion(new Region(vecRowCounting, (short)0,  (vecRowCounting+ orderVoList.get(veC).getOrVoList().size()), (short)0 ));
        					
        					// regList.add(new Region(vecRowCounting , (short)0, (vecRowCounting+ orderVoList.get(veC).getOrVoList().size()), (short)0 ));
        					
        					cell = row.createCell(0);
        					cell.setCellValue(orderVoList.get(veC).getOrBuyerName()+"/"+orderVoList.get(veC).getOrReceiverName());
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, true,false,false,true,false,false);
        					sheet.addMergedRegion(new CellRangeAddress(vecRowCounting, vecRowCounting + orderVoList.get(veC).getOrVoList().size()-1,  0, 0));
        					
        				    
        				}else if(q + 1 == orderVoList.get(veC).getOrVoList().size()){
        					cell = row.createCell(0);
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, false,true,false,true,false,false);
        					
        				}else if(q > 0 && q + 1 < orderVoList.get(veC).getOrVoList().size()) {
        					cell = row.createCell(0);
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, false,false,true,true,false,false);
        				}

        				
        				cell = row.createCell(1);
            			cell.setCellValue(orderVoList.get(veC).getOrVoList().get(q).getOrProduct() + "[ "+orderVoList.get(veC).getOrVoList().get(q).getOrProductOption()+" ]");
            			
            			if(q == 0){
            				cs.setCellsBorderStyle(style, sheet, workbook, cell, true,false,false,false,false,false);

            			}else if(q + 1 == orderVoList.get(veC).getOrVoList().size()){
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, false,false,true,false,false,false);
            				
            			}else if(q > 0 && q + 1 < orderVoList.get(veC).getOrVoList().size()) {
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, false,true,false,false,true,false);
        				}

            			cell = row.createCell(2);
            			cell.setCellValue(orderVoList.get(veC).getOrVoList().get(q).getOrAmount());

    				    
        				if(q == 0){
            				//테두리 스타일
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, true,false,false,false,false,true);

        				}else if(q + 1 == orderVoList.get(veC).getOrVoList().size()){
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, false,false,true,false,false,true);
        					
        				}else if(q > 0 && q + 1 < orderVoList.get(veC).getOrVoList().size()) {
        					cs.setCellsBorderStyle(style, sheet, workbook, cell, false,false,false,false,false,true);
        				}

        				vecRowCounting++;
        			}
        			
        			
            		
        		}
        		
			}

	        Date day = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        String filedate = sdf.format(day);
	        String fileWrite = fileName+" ["+filedate+"].xls";	        
	        
	        // 입력된 내용 파일로 쓰기
	        File file = new File(upPath, fileWrite);
	        FileOutputStream fos = null;
	        
	        try {
	            fos = new FileOutputStream(file);
	            workbook.write(fos);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	            	
	                if(fos!=null) fos.close();
	                
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        
	        return file;
	 }
	 
	 public File labelXlsWriter(List<String> idxTitle, List<PrintDataSetVO> labelList, List<ExcelOrderSeqVO> eoSeq, String upPath, String fileName) {
		 
		 	osGs = new OrdersVOGetterSetting();
		 	
	        // 워크북 생성
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        
	        CellStyle style = workbook.createCellStyle();
	        // 워크시트 생성
	        HSSFSheet sheet = workbook.createSheet();
	        // 행 생성
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)500);
	        // 쎌 생성
	        HSSFCell cell;
	        
	        // 헤더 정보 구성
	        for(int i = 0; i < idxTitle.size(); i++) {
	        	cell = row.createCell(i);
	        	cell.setCellValue(idxTitle.get(i));
	        	cs.setCellsStylesSpecial(style, sheet, workbook, cell);
	        	
	        }
	        
	        // 리스트의 size 만큼 row를 생성
	        
	        int rowCounting = 1;
	        boolean productEx = false;
	        
	        for(int rowIdx=0 ; rowIdx < labelList.size(); rowIdx++) {
	        	row = sheet.createRow(rowCounting);
    			//행 정보 생성
					
				cell = row.createCell(0);
				cell.setCellValue(labelList.get(rowIdx).getQty());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(1);
				cell.setCellValue(labelList.get(rowIdx).getProduct());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(2);
				cell.setCellValue(labelList.get(rowIdx).getWeight());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(3);
				cell.setCellValue(labelList.get(rowIdx).getCountryOfOrigin());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(4);
				cell.setCellValue(labelList.get(rowIdx).getRawMeterials());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(5);
				cell.setCellValue(labelList.get(rowIdx).getSellByDate());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(6);
				cell.setCellValue(labelList.get(rowIdx).getStorageMethod());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(7);
				cell.setCellValue(labelList.get(rowIdx).getLevels());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(8);
				cell.setCellValue(labelList.get(rowIdx).getItemsManufNum());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(9);
				cell.setCellValue(labelList.get(rowIdx).getAbattoir());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(10);
				cell.setCellValue(labelList.get(rowIdx).getAnimalProdTraceNum());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);
				
				cell = row.createCell(11);
				cell.setCellValue(labelList.get(rowIdx).getBarcodeNum());
				cs.setCellsStylesSpecial(style, sheet, workbook, cell);		
					
				rowCounting++;
	           
	        }//for

	        Date day = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        String filedate = sdf.format(day);
	        String fileWrite = fileName+" ["+filedate+"].xls";	        
	        
	        // 입력된 내용 파일로 쓰기
	        File file = new File(upPath, fileWrite);
	        FileOutputStream fos = null;
	        
	        try {
	            fos = new FileOutputStream(file);
	            workbook.write(fos);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	            	
	                if(fos!=null) fos.close();
	                
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        
	        return file;
	 }

}
