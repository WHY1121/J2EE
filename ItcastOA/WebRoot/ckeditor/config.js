/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.language='zh-cn';
	config.width='99%';
	config.height='100%';
	config.toolbar = 'MyToolbar';
	 //1   配置工具栏
	config.toolbar_MyToolbar =
	[
		{ name: 'document', items : [ 'NewPage','Preview' ] },
		{ name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
		{ name: 'editing', items : [ 'Find','Replace','-','SelectAll','-','Scayt' ] },
		{ name: 'insert', items : [ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'
                 ,'Iframe' ] },
        { name: 'links', items : [ 'Link','Unlink','Anchor' ] },
                '/',
		{ name: 'styles', items : [ 'Styles','Format','Font','FontSize' ] },
		{ name: 'basicstyles', items : [ 'Bold','Italic','Strike','-','RemoveFormat' ] },
		{ name: 'colors', items : [ 'TextColor','BGColor' ] },
		{ name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote' ] },
		
		{ name: 'tools', items : [ 'Maximize','-','About' ] }
	];
	//配置中文字体
	config.font_names = '宋体;楷体_GB2312;黑体;隶书;Times New Roman;Arial' ;
	//去掉左下角的body和p标签
	config.removePlugins = 'elementspath';   
	 //配置表情图片
	 //表情的地址
    config.smiley_path =CKEDITOR.basePath+"plugins/smiley/images/";
    config.smiley_images = [
'0.gif','1.gif','2.gif','3.gif','4.gif','5.gif','6.gif','7.gif','8.gif','9.gif','10.gif',
'11.gif','12.gif','13.gif','14.gif','15.gif','16.gif','17.gif','18.gif','19.gif','20.gif',
'21.gif','22.gif','23.gif','24.gif','25.gif','26.gif','27.gif','28.gif','29.gif','30.gif',
'31.gif','32.gif','33.gif','34.gif','35.gif','36.gif','37.gif','38.gif','39.gif','40.gif',
'41.gif','42.gif','43.gif','44.gif','45.gif','46.gif','47.gif','48.gif','49.gif','50.gif',
'51.gif','52.gif','53.gif','54.gif','55.gif','56.gif','57.gif','58.gif','59.gif','60.gif',
'61.gif','62.gif','63.gif','64.gif','65.gif','66.gif','67.gif','68.gif','69.gif','70.gif',
'71.gif','72.gif','73.gif','74.gif','75.gif','76.gif','77.gif','78.gif','79.gif','80.gif',
'81.gif','82.gif','83.gif','84.gif','85.gif','86.gif','87.gif','88.gif','89.gif','90.gif',
'91.gif','92.gif','93.gif','94.gif','95.gif','96.gif','97.gif','98.gif','test.gif'];
   
};
