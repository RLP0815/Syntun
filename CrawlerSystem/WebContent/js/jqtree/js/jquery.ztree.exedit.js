!function(e){var t={event:{DRAG:"ztree_drag",DROP:"ztree_drop",RENAME:"ztree_rename",DRAGMOVE:"ztree_dragmove"},id:{EDIT:"_edit",INPUT:"_input",REMOVE:"_remove"},move:{TYPE_INNER:"inner",TYPE_PREV:"prev",TYPE_NEXT:"next"},node:{CURSELECTED_EDIT:"curSelectedNode_Edit",TMPTARGET_TREE:"tmpTargetzTree",TMPTARGET_NODE:"tmpTargetNode"}},o={edit:{enable:!1,editNameSelectAll:!1,showRemoveBtn:!0,showRenameBtn:!0,removeTitle:"remove",renameTitle:"rename",drag:{autoExpandTrigger:!1,isCopy:!0,isMove:!0,prev:!0,next:!0,inner:!0,minMoveSize:5,borderMax:10,borderMin:-5,maxShowNodeNum:5,autoOpenTime:500}},view:{addHoverDom:null,removeHoverDom:null},callback:{beforeDrag:null,beforeDragOpen:null,beforeDrop:null,beforeEditName:null,beforeRename:null,onDrag:null,onDragMove:null,onDrop:null,onRename:null}},r=function(e){var t=I.getRoot(e),o=I.getRoots();t.curEditNode=null,t.curEditInput=null,t.curHoverNode=null,t.dragFlag=0,t.dragNodeShowBefore=[],t.dragMaskList=new Array,o.showHoverDom=!0},d=function(){},n=function(e){var t=e.treeObj,o=f.event;t.bind(o.RENAME,function(t,o,r,d){T.apply(e.callback.onRename,[t,o,r,d])}),t.bind(o.DRAG,function(t,o,r,d){T.apply(e.callback.onDrag,[o,r,d])}),t.bind(o.DRAGMOVE,function(t,o,r,d){T.apply(e.callback.onDragMove,[o,r,d])}),t.bind(o.DROP,function(t,o,r,d,n,a,i){T.apply(e.callback.onDrop,[o,r,d,n,a,i])})},a=function(e){var t=e.treeObj,o=f.event;t.unbind(o.RENAME),t.unbind(o.DRAG),t.unbind(o.DRAGMOVE),t.unbind(o.DROP)},i=function(e){var t=e.target,o=I.getSetting(e.data.treeId),r=e.relatedTarget,d="",n=null,a="",i="",l=null,s=null,c=null;if(T.eqs(e.type,"mouseover")?(c=T.getMDom(o,t,[{tagName:"a",attrName:"treeNode"+f.id.A}]),c&&(d=T.getNodeMainDom(c).id,a="hoverOverNode")):T.eqs(e.type,"mouseout")?(c=T.getMDom(o,r,[{tagName:"a",attrName:"treeNode"+f.id.A}]),c||(d="remove",a="hoverOutNode")):T.eqs(e.type,"mousedown")&&(c=T.getMDom(o,t,[{tagName:"a",attrName:"treeNode"+f.id.A}]),c&&(d=T.getNodeMainDom(c).id,a="mousedownNode")),d.length>0)switch(n=I.getNodeCache(o,d),a){case"mousedownNode":l=N.onMousedownNode;break;case"hoverOverNode":l=N.onHoverOverNode;break;case"hoverOutNode":l=N.onHoverOutNode}var u={stop:!1,node:n,nodeEventType:a,nodeEventCallback:l,treeEventType:i,treeEventCallback:s};return u},l=function(e,t,o){o&&(o.isHover=!1,o.editNameFlag=!1)},s=function(e,t){t.cancelEditName=function(e){var t=I.getRoot(this.setting);t.curEditNode&&E.cancelCurEditNode(this.setting,e?e:null,!0)},t.copyNode=function(e,t,o,r){function d(){E.addNodes(n.setting,e,-1,[a],r)}if(!t)return null;if(e&&!e.isParent&&this.setting.data.keep.leaf&&o===f.move.TYPE_INNER)return null;var n=this,a=T.clone(t);return e||(e=null,o=f.move.TYPE_INNER),o==f.move.TYPE_INNER?T.canAsync(this.setting,e)?E.asyncNode(this.setting,e,r,d):d():(E.addNodes(this.setting,e.parentNode,-1,[a],r),E.moveNode(this.setting,e,a,o,!1,r)),a},t.editName=function(e){e&&e.tId&&e===I.getNodeCache(this.setting,e.tId)&&(e.parentTId&&E.expandCollapseParentNode(this.setting,e.getParentNode(),!0),E.editNode(this.setting,e))},t.moveNode=function(e,t,o,r){function d(){E.moveNode(n.setting,e,t,o,!1,r)}if(!t)return t;if(e&&!e.isParent&&this.setting.data.keep.leaf&&o===f.move.TYPE_INNER)return null;if(e&&(t.parentTId==e.tId&&o==f.move.TYPE_INNER||h(t,this.setting).find("#"+e.tId).length>0))return null;e||(e=null);var n=this;return T.canAsync(this.setting,e)&&o===f.move.TYPE_INNER?E.asyncNode(this.setting,e,r,d):d(),t},t.setEditable=function(e){return this.setting.edit.enable=e,this.refresh()}},c={setSonNodeLevel:function(e,t,o){if(o){var r=e.data.key.children;if(o.level=t?t.level+1:0,o[r])for(var d=0,n=o[r].length;n>d;d++)o[r][d]&&I.setSonNodeLevel(e,o,o[r][d])}}},u={},N={onHoverOverNode:function(e,t){var o=I.getSetting(e.data.treeId),r=I.getRoot(o);r.curHoverNode!=t&&N.onHoverOutNode(e),r.curHoverNode=t,E.addHoverDom(o,t)},onHoverOutNode:function(e){var t=I.getSetting(e.data.treeId),o=I.getRoot(t);o.curHoverNode&&!I.isSelectedNode(t,o.curHoverNode)&&(E.removeTreeDom(t,o.curHoverNode),o.curHoverNode=null)},onMousedownNode:function(o,r){function d(o){function r(){b=null,k="",S=f.move.TYPE_INNER,p.css({display:"none"}),window.zTreeMoveTimer&&(clearTimeout(window.zTreeMoveTimer),window.zTreeMoveTargetNodeTId=null)}if(0==c.dragFlag&&Math.abs(A-o.clientX)<s.edit.drag.minMoveSize&&Math.abs(L-o.clientY)<s.edit.drag.minMoveSize)return!0;var d,a,i,l,N,g=s.data.key.children;if(M.css("cursor","pointer"),0==c.dragFlag){if(0==T.apply(s.callback.beforeDrag,[s.treeId,v],!0))return n(o),!0;for(d=0,a=v.length;a>d;d++)0==d&&(c.dragNodeShowBefore=[]),i=v[d],i.isParent&&i.open?(E.expandCollapseNode(s,i,!i.open),c.dragNodeShowBefore[i.tId]=!0):c.dragNodeShowBefore[i.tId]=!1;c.dragFlag=1,u.showHoverDom=!1,T.showIfameMask(s,!0);var O=!0,x=-1;if(v.length>1){var j=v[0].parentTId?v[0].getParentNode()[g]:I.getNodes(s);for(N=[],d=0,a=j.length;a>d;d++)if(void 0!==c.dragNodeShowBefore[j[d].tId]&&(O&&x>-1&&x+1!==d&&(O=!1),N.push(j[d]),x=d),v.length===N.length){v=N;break}}for(O&&(R=v[0].getPreNode(),P=v[v.length-1].getNextNode()),m=h("<ul class='zTreeDragUL'></ul>",s),d=0,a=v.length;a>d;d++)i=v[d],i.editNameFlag=!1,E.selectNode(s,i,d>0),E.removeTreeDom(s,i),d>s.edit.drag.maxShowNodeNum-1||(l=h("<li id='"+i.tId+"_tmp'></li>",s),l.append(h(i,f.id.A,s).clone()),l.css("padding","0"),l.children("#"+i.tId+f.id.A).removeClass(f.node.CURSELECTED),m.append(l),d==s.edit.drag.maxShowNodeNum-1&&(l=h("<li id='"+i.tId+"_moretmp'><a>  ...  </a></li>",s),m.append(l)));m.attr("id",v[0].tId+f.id.UL+"_tmp"),m.addClass(s.treeObj.attr("class")),m.appendTo(M),p=h("<span class='tmpzTreeMove_arrow'></span>",s),p.attr("id","zTreeMove_arrow_tmp"),p.appendTo(M),s.treeObj.trigger(f.event.DRAG,[o,s.treeId,v])}if(1==c.dragFlag){if(b&&p.attr("id")==o.target.id&&k&&o.clientX+w.scrollLeft()+2>e("#"+k+f.id.A,b).offset().left){var z=e("#"+k+f.id.A,b);o.target=z.length>0?z.get(0):o.target}else b&&(b.removeClass(f.node.TMPTARGET_TREE),k&&e("#"+k+f.id.A,b).removeClass(f.node.TMPTARGET_NODE+"_"+f.move.TYPE_PREV).removeClass(f.node.TMPTARGET_NODE+"_"+t.move.TYPE_NEXT).removeClass(f.node.TMPTARGET_NODE+"_"+t.move.TYPE_INNER));b=null,k=null,C=!1,_=s;var B=I.getSettings();for(var H in B)B[H].treeId&&B[H].edit.enable&&B[H].treeId!=s.treeId&&(o.target.id==B[H].treeId||e(o.target).parents("#"+B[H].treeId).length>0)&&(C=!0,_=B[H]);var F=w.scrollTop(),V=w.scrollLeft(),U=_.treeObj.offset(),G=_.treeObj.get(0).scrollHeight,X=_.treeObj.get(0).scrollWidth,q=o.clientY+F-U.top,W=_.treeObj.height()+U.top-o.clientY-F,K=o.clientX+V-U.left,Q=_.treeObj.width()+U.left-o.clientX-V,Z=q<s.edit.drag.borderMax&&q>s.edit.drag.borderMin,$=W<s.edit.drag.borderMax&&W>s.edit.drag.borderMin,J=K<s.edit.drag.borderMax&&K>s.edit.drag.borderMin,et=Q<s.edit.drag.borderMax&&Q>s.edit.drag.borderMin,tt=q>s.edit.drag.borderMin&&W>s.edit.drag.borderMin&&K>s.edit.drag.borderMin&&Q>s.edit.drag.borderMin,ot=Z&&_.treeObj.scrollTop()<=0,rt=$&&_.treeObj.scrollTop()+_.treeObj.height()+10>=G,dt=J&&_.treeObj.scrollLeft()<=0,nt=et&&_.treeObj.scrollLeft()+_.treeObj.width()+10>=X;if(o.target&&T.isChildOrSelf(o.target,_.treeId)){for(var at=o.target;at&&at.tagName&&!T.eqs(at.tagName,"li")&&at.id!=_.treeId;)at=at.parentNode;var it=!0;for(d=0,a=v.length;a>d;d++){if(i=v[d],at.id===i.tId){it=!1;break}if(h(i,s).find("#"+at.id).length>0){it=!1;break}}it&&o.target&&T.isChildOrSelf(o.target,at.id+f.id.A)&&(b=e(at),k=at.id)}i=v[0],tt&&T.isChildOrSelf(o.target,_.treeId)&&(!b&&(o.target.id==_.treeId||ot||rt||dt||nt)&&(C||!C&&i.parentTId)&&(b=_.treeObj),Z?_.treeObj.scrollTop(_.treeObj.scrollTop()-10):$&&_.treeObj.scrollTop(_.treeObj.scrollTop()+10),J?_.treeObj.scrollLeft(_.treeObj.scrollLeft()-10):et&&_.treeObj.scrollLeft(_.treeObj.scrollLeft()+10),b&&b!=_.treeObj&&b.offset().left<_.treeObj.offset().left&&_.treeObj.scrollLeft(_.treeObj.scrollLeft()+b.offset().left-_.treeObj.offset().left)),m.css({top:o.clientY+F+3+"px",left:o.clientX+V+3+"px"});var lt=0,st=0;if(b&&b.attr("id")!=_.treeId){var ct=null==k?null:I.getNodeCache(_,k),ut=(o.ctrlKey||o.metaKey)&&s.edit.drag.isMove&&s.edit.drag.isCopy||!s.edit.drag.isMove&&s.edit.drag.isCopy,Nt=!(!R||k!==R.tId),gt=!(!P||k!==P.tId),vt=i.parentTId&&i.parentTId==k,mt=(ut||!gt)&&T.apply(_.edit.drag.prev,[_.treeId,v,ct],!!_.edit.drag.prev),pt=(ut||!Nt)&&T.apply(_.edit.drag.next,[_.treeId,v,ct],!!_.edit.drag.next),Tt=!(!ut&&vt||_.data.keep.leaf&&!ct.isParent||!T.apply(_.edit.drag.inner,[_.treeId,v,ct],!!_.edit.drag.inner));if(mt||pt||Tt){var ft=e("#"+k+f.id.A,b),Et=ct.isLastNode?null:e("#"+ct.getNextNode().tId+f.id.A,b.next()),It=ft.offset().top,ht=ft.offset().left,bt=mt?Tt?.25:pt?.5:1:-1,Rt=pt?Tt?.75:mt?.5:0:-1,Pt=(o.clientY+F-It)/ft.height();if((1==bt||bt>=Pt&&Pt>=-.2)&&mt?(lt=1-p.width(),st=It-p.height()/2,S=f.move.TYPE_PREV):(0==Rt||Pt>=Rt&&1.2>=Pt)&&pt?(lt=1-p.width(),st=null==Et||ct.isParent&&ct.open?It+ft.height()-p.height()/2:Et.offset().top-p.height()/2,S=f.move.TYPE_NEXT):Tt?(lt=5-p.width(),st=It,S=f.move.TYPE_INNER):r(),b&&(p.css({display:"block",top:st+"px",left:ht+lt+"px"}),ft.addClass(f.node.TMPTARGET_NODE+"_"+S),(D!=k||y!=S)&&(Y=(new Date).getTime()),ct&&ct.isParent&&S==f.move.TYPE_INNER)){var wt=!0;window.zTreeMoveTimer&&window.zTreeMoveTargetNodeTId!==ct.tId?(clearTimeout(window.zTreeMoveTimer),window.zTreeMoveTargetNodeTId=null):window.zTreeMoveTimer&&window.zTreeMoveTargetNodeTId===ct.tId&&(wt=!1),wt&&(window.zTreeMoveTimer=setTimeout(function(){S==f.move.TYPE_INNER&&ct&&ct.isParent&&!ct.open&&(new Date).getTime()-Y>_.edit.drag.autoOpenTime&&T.apply(_.callback.beforeDragOpen,[_.treeId,ct],!0)&&(E.switchNode(_,ct),_.edit.drag.autoExpandTrigger&&_.treeObj.trigger(f.event.EXPAND,[_.treeId,ct]))},_.edit.drag.autoOpenTime+50),window.zTreeMoveTargetNodeTId=ct.tId)}}else r()}else S=f.move.TYPE_INNER,b&&T.apply(_.edit.drag.inner,[_.treeId,v,null],!!_.edit.drag.inner)?b.addClass(f.node.TMPTARGET_TREE):b=null,p.css({display:"none"}),window.zTreeMoveTimer&&(clearTimeout(window.zTreeMoveTimer),window.zTreeMoveTargetNodeTId=null);D=k,y=S,s.treeObj.trigger(f.event.DRAGMOVE,[o,s.treeId,v])}return!1}function n(o){function r(){if(C){if(!g)for(var e=0,t=v.length;t>e;e++)E.removeNode(s,v[e]);S==f.move.TYPE_INNER?E.addNodes(_,R,-1,P):E.addNodes(_,R.getParentNode(),S==f.move.TYPE_PREV?R.getIndex():R.getIndex()+1,P)}else if(g&&S==f.move.TYPE_INNER)E.addNodes(_,R,-1,P);else if(g)E.addNodes(_,R.getParentNode(),S==f.move.TYPE_PREV?R.getIndex():R.getIndex()+1,P);else if(S!=f.move.TYPE_NEXT)for(e=0,t=P.length;t>e;e++)E.moveNode(_,R,P[e],S,!1);else for(e=-1,t=P.length-1;t>e;t--)E.moveNode(_,R,P[t],S,!1);E.selectNodes(_,P);var r=h(P[0],s).get(0);E.scrollIntoView(r),s.treeObj.trigger(f.event.DROP,[o,_.treeId,P,R,S,g])}if(window.zTreeMoveTimer&&(clearTimeout(window.zTreeMoveTimer),window.zTreeMoveTargetNodeTId=null),D=null,y=null,w.unbind("mousemove",d),w.unbind("mouseup",n),w.unbind("selectstart",a),M.css("cursor","auto"),b&&(b.removeClass(f.node.TMPTARGET_TREE),k&&e("#"+k+f.id.A,b).removeClass(f.node.TMPTARGET_NODE+"_"+f.move.TYPE_PREV).removeClass(f.node.TMPTARGET_NODE+"_"+t.move.TYPE_NEXT).removeClass(f.node.TMPTARGET_NODE+"_"+t.move.TYPE_INNER)),T.showIfameMask(s,!1),u.showHoverDom=!0,0!=c.dragFlag){c.dragFlag=0;var i,l,N;for(i=0,l=v.length;l>i;i++)N=v[i],N.isParent&&c.dragNodeShowBefore[N.tId]&&!N.open&&(E.expandCollapseNode(s,N,!N.open),delete c.dragNodeShowBefore[N.tId]);m&&m.remove(),p&&p.remove();var g=(o.ctrlKey||o.metaKey)&&s.edit.drag.isMove&&s.edit.drag.isCopy||!s.edit.drag.isMove&&s.edit.drag.isCopy;if(!g&&b&&k&&v[0].parentTId&&k==v[0].parentTId&&S==f.move.TYPE_INNER&&(b=null),b){var R=null==k?null:I.getNodeCache(_,k);if(0==T.apply(s.callback.beforeDrop,[_.treeId,v,R,S,g],!0))return void E.selectNodes(O,v);var P=g?T.clone(v):v;S==f.move.TYPE_INNER&&T.canAsync(_,R)?E.asyncNode(_,R,!1,r):r()}else E.selectNodes(O,v),s.treeObj.trigger(f.event.DROP,[o,s.treeId,v,null,null,null])}}function a(){return!1}var i,l,s=I.getSetting(o.data.treeId),c=I.getRoot(s),u=I.getRoots();if(2==o.button||!s.edit.enable||!s.edit.drag.isCopy&&!s.edit.drag.isMove)return!0;var N=o.target,g=I.getRoot(s).curSelectedList,v=[];if(I.isSelectedNode(s,r))for(i=0,l=g.length;l>i;i++){if(g[i].editNameFlag&&T.eqs(N.tagName,"input")&&null!==N.getAttribute("treeNode"+f.id.INPUT))return!0;if(v.push(g[i]),v[0].parentTId!==g[i].parentTId){v=[r];break}}else v=[r];E.editNodeBlur=!0,E.cancelCurEditNode(s);var m,p,b,R,P,w=e(s.treeObj.get(0).ownerDocument),M=e(s.treeObj.get(0).ownerDocument.body),C=!1,_=s,O=s,D=null,y=null,k=null,S=f.move.TYPE_INNER,A=o.clientX,L=o.clientY,Y=(new Date).getTime();return T.uCanDo(s)&&w.bind("mousemove",d),w.bind("mouseup",n),w.bind("selectstart",a),o.preventDefault&&o.preventDefault(),!0}},g={getAbs:function(e){var t=e.getBoundingClientRect(),o=document.body.scrollTop+document.documentElement.scrollTop,r=document.body.scrollLeft+document.documentElement.scrollLeft;return[t.left+r,t.top+o]},inputFocus:function(e){e.get(0)&&(e.focus(),T.setCursorPosition(e.get(0),e.val().length))},inputSelect:function(e){e.get(0)&&(e.focus(),e.select())},setCursorPosition:function(e,t){if(e.setSelectionRange)e.focus(),e.setSelectionRange(t,t);else if(e.createTextRange){var o=e.createTextRange();o.collapse(!0),o.moveEnd("character",t),o.moveStart("character",t),o.select()}},showIfameMask:function(e,t){for(var o=I.getRoot(e);o.dragMaskList.length>0;)o.dragMaskList[0].remove(),o.dragMaskList.shift();if(t)for(var r=h("iframe",e),d=0,n=r.length;n>d;d++){var a=r.get(d),i=T.getAbs(a),l=h("<div id='zTreeMask_"+d+"' class='zTreeMask' style='top:"+i[1]+"px; left:"+i[0]+"px; width:"+a.offsetWidth+"px; height:"+a.offsetHeight+"px;'></div>",e);l.appendTo(h("body",e)),o.dragMaskList.push(l)}}},v={addEditBtn:function(e,t){if(!(t.editNameFlag||h(t,f.id.EDIT,e).length>0)&&T.apply(e.edit.showRenameBtn,[e.treeId,t],e.edit.showRenameBtn)){var o=h(t,f.id.A,e),r="<span class='"+f.className.BUTTON+" edit' id='"+t.tId+f.id.EDIT+"' title='"+T.apply(e.edit.renameTitle,[e.treeId,t],e.edit.renameTitle)+"' treeNode"+f.id.EDIT+" style='display:none;'></span>";o.append(r),h(t,f.id.EDIT,e).bind("click",function(){return T.uCanDo(e)&&0!=T.apply(e.callback.beforeEditName,[e.treeId,t],!0)?(E.editNode(e,t),!1):!1}).show()}},addRemoveBtn:function(e,t){if(!(t.editNameFlag||h(t,f.id.REMOVE,e).length>0)&&T.apply(e.edit.showRemoveBtn,[e.treeId,t],e.edit.showRemoveBtn)){var o=h(t,f.id.A,e),r="<span class='"+f.className.BUTTON+" remove' id='"+t.tId+f.id.REMOVE+"' title='"+T.apply(e.edit.removeTitle,[e.treeId,t],e.edit.removeTitle)+"' treeNode"+f.id.REMOVE+" style='display:none;'></span>";o.append(r),h(t,f.id.REMOVE,e).bind("click",function(){return T.uCanDo(e)&&0!=T.apply(e.callback.beforeRemove,[e.treeId,t],!0)?(E.removeNode(e,t),e.treeObj.trigger(f.event.REMOVE,[e.treeId,t]),!1):!1}).bind("mousedown",function(){return!0}).show()}},addHoverDom:function(e,t){I.getRoots().showHoverDom&&(t.isHover=!0,e.edit.enable&&(E.addEditBtn(e,t),E.addRemoveBtn(e,t)),T.apply(e.view.addHoverDom,[e.treeId,t]))},cancelCurEditNode:function(e,t,o){var r=I.getRoot(e),d=e.data.key.name,n=r.curEditNode;if(n){var a=r.curEditInput,i=t?t:o?n[d]:a.val();if(T.apply(e.callback.beforeRename,[e.treeId,n,i,o],!0)===!1)return!1;n[d]=i;var l=h(n,f.id.A,e);l.removeClass(f.node.CURSELECTED_EDIT),a.unbind(),E.setNodeName(e,n),n.editNameFlag=!1,r.curEditNode=null,r.curEditInput=null,E.selectNode(e,n,!1),e.treeObj.trigger(f.event.RENAME,[e.treeId,n,o])}return r.noSelection=!0,!0},editNode:function(e,t){var o=I.getRoot(e);if(E.editNodeBlur=!1,I.isSelectedNode(e,t)&&o.curEditNode==t&&t.editNameFlag)return void setTimeout(function(){T.inputFocus(o.curEditInput)},0);var r=e.data.key.name;t.editNameFlag=!0,E.removeTreeDom(e,t),E.cancelCurEditNode(e),E.selectNode(e,t,!1),h(t,f.id.SPAN,e).html("<input type=text class='rename' id='"+t.tId+f.id.INPUT+"' treeNode"+f.id.INPUT+" >");var d=h(t,f.id.INPUT,e);d.attr("value",t[r]),e.edit.editNameSelectAll?T.inputSelect(d):T.inputFocus(d),d.bind("blur",function(){E.editNodeBlur||E.cancelCurEditNode(e)}).bind("keydown",function(t){"13"==t.keyCode?(E.editNodeBlur=!0,E.cancelCurEditNode(e)):"27"==t.keyCode&&E.cancelCurEditNode(e,null,!0)}).bind("click",function(){return!1}).bind("dblclick",function(){return!1}),h(t,f.id.A,e).addClass(f.node.CURSELECTED_EDIT),o.curEditInput=d,o.noSelection=!1,o.curEditNode=t},moveNode:function(e,t,o,r,d,n){var a=I.getRoot(e),i=e.data.key.children;if(t!=o&&(!e.data.keep.leaf||!t||t.isParent||r!=f.move.TYPE_INNER)){var l=o.parentTId?o.getParentNode():a,s=null===t||t==a;s&&null===t&&(t=a),s&&(r=f.move.TYPE_INNER);var c=t.parentTId?t.getParentNode():a;r!=f.move.TYPE_PREV&&r!=f.move.TYPE_NEXT&&(r=f.move.TYPE_INNER),r==f.move.TYPE_INNER&&(s?o.parentTId=null:(t.isParent||(t.isParent=!0,t.open=!!t.open,E.setNodeLineIcos(e,t)),o.parentTId=t.tId));var u,N;if(s)u=e.treeObj,N=u;else{if(n||r!=f.move.TYPE_INNER?n||E.expandCollapseNode(e,t.getParentNode(),!0,!1):E.expandCollapseNode(e,t,!0,!1),u=h(t,e),N=h(t,f.id.UL,e),u.get(0)&&!N.get(0)){var g=[];E.makeUlHtml(e,t,g,""),u.append(g.join(""))}N=h(t,f.id.UL,e)}var v=h(o,e);v.get(0)?u.get(0)||v.remove():v=E.appendNodes(e,o.level,[o],null,-1,!1,!0).join(""),N.get(0)&&r==f.move.TYPE_INNER?N.append(v):u.get(0)&&r==f.move.TYPE_PREV?u.before(v):u.get(0)&&r==f.move.TYPE_NEXT&&u.after(v);var m,p,T=-1,b=0,R=null,P=null,w=o.level;if(o.isFirstNode)T=0,l[i].length>1&&(R=l[i][1],R.isFirstNode=!0);else if(o.isLastNode)T=l[i].length-1,R=l[i][T-1],R.isLastNode=!0;else for(m=0,p=l[i].length;p>m;m++)if(l[i][m].tId==o.tId){T=m;break}if(T>=0&&l[i].splice(T,1),r!=f.move.TYPE_INNER)for(m=0,p=c[i].length;p>m;m++)c[i][m].tId==t.tId&&(b=m);if(r==f.move.TYPE_INNER?(t[i]||(t[i]=new Array),t[i].length>0&&(P=t[i][t[i].length-1],P.isLastNode=!1),t[i].splice(t[i].length,0,o),o.isLastNode=!0,o.isFirstNode=1==t[i].length):t.isFirstNode&&r==f.move.TYPE_PREV?(c[i].splice(b,0,o),P=t,P.isFirstNode=!1,o.parentTId=t.parentTId,o.isFirstNode=!0,o.isLastNode=!1):t.isLastNode&&r==f.move.TYPE_NEXT?(c[i].splice(b+1,0,o),P=t,P.isLastNode=!1,o.parentTId=t.parentTId,o.isFirstNode=!1,o.isLastNode=!0):(r==f.move.TYPE_PREV?c[i].splice(b,0,o):c[i].splice(b+1,0,o),o.parentTId=t.parentTId,o.isFirstNode=!1,o.isLastNode=!1),I.fixPIdKeyValue(e,o),I.setSonNodeLevel(e,o.getParentNode(),o),E.setNodeLineIcos(e,o),E.repairNodeLevelClass(e,o,w),!e.data.keep.parent&&l[i].length<1){l.isParent=!1,l.open=!1;var M=h(l,f.id.UL,e),C=h(l,f.id.SWITCH,e),_=h(l,f.id.ICON,e);E.replaceSwitchClass(l,C,f.folder.DOCU),E.replaceIcoClass(l,_,f.folder.DOCU),M.css("display","none")}else R&&E.setNodeLineIcos(e,R);P&&E.setNodeLineIcos(e,P),e.check&&e.check.enable&&E.repairChkClass&&(E.repairChkClass(e,l),E.repairParentChkClassWithSelf(e,l),l!=o.parent&&E.repairParentChkClassWithSelf(e,o)),n||E.expandCollapseParentNode(e,o.getParentNode(),!0,d)}},removeEditBtn:function(e,t){h(t,f.id.EDIT,e).unbind().remove()},removeRemoveBtn:function(e,t){h(t,f.id.REMOVE,e).unbind().remove()},removeTreeDom:function(e,t){t.isHover=!1,E.removeEditBtn(e,t),E.removeRemoveBtn(e,t),T.apply(e.view.removeHoverDom,[e.treeId,t])},repairNodeLevelClass:function(e,t,o){if(o!==t.level){var r=h(t,e),d=h(t,f.id.A,e),n=h(t,f.id.UL,e),a=f.className.LEVEL+o,i=f.className.LEVEL+t.level;r.removeClass(a),r.addClass(i),d.removeClass(a),d.addClass(i),n.removeClass(a),n.addClass(i)}},selectNodes:function(e,t){for(var o=0,r=t.length;r>o;o++)E.selectNode(e,t[o],o>0)}},m={tools:g,view:v,event:u,data:c};e.extend(!0,e.fn.zTree.consts,t),e.extend(!0,e.fn.zTree._z,m);var p=e.fn.zTree,T=p._z.tools,f=p.consts,E=p._z.view,I=p._z.data,h=(p._z.event,T.$);I.exSetting(o),I.addInitBind(n),I.addInitUnBind(a),I.addInitCache(d),I.addInitNode(l),I.addInitProxy(i),I.addInitRoot(r),I.addZTreeTools(s);var b=E.cancelPreSelectedNode;E.cancelPreSelectedNode=function(e,t){for(var o=I.getRoot(e).curSelectedList,r=0,d=o.length;d>r&&(t&&t!==o[r]||(E.removeTreeDom(e,o[r]),!t));r++);b&&b.apply(E,arguments)};var R=E.createNodes;E.createNodes=function(e,t,o,r){R&&R.apply(E,arguments),o&&E.repairParentChkClassWithSelf&&E.repairParentChkClassWithSelf(e,r)};var P=E.makeNodeUrl;E.makeNodeUrl=function(e){return e.edit.enable?null:P.apply(E,arguments)};var w=E.removeNode;E.removeNode=function(e,t){var o=I.getRoot(e);o.curEditNode===t&&(o.curEditNode=null),w&&w.apply(E,arguments)};var M=E.selectNode;E.selectNode=function(e,t){var o=I.getRoot(e);return I.isSelectedNode(e,t)&&o.curEditNode==t&&t.editNameFlag?!1:(M&&M.apply(E,arguments),E.addHoverDom(e,t),!0)};var C=T.uCanDo;T.uCanDo=function(e,t){var o=I.getRoot(e);return t&&(T.eqs(t.type,"mouseover")||T.eqs(t.type,"mouseout")||T.eqs(t.type,"mousedown")||T.eqs(t.type,"mouseup"))?!0:(o.curEditNode&&(E.editNodeBlur=!1,o.curEditInput.focus()),!o.curEditNode&&(C?C.apply(E,arguments):!0))}}(jQuery);