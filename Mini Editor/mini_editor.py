from tkinter import *
from tkinter import filedialog
from PIL import Image, ImageTk, ImageOps, ImageEnhance
import matplotlib.pyplot as plt
import os
from tkinter import messagebox


pic = ''
img_alterada = ''


'''FUNÇÕES DOS BOTÕES'''

# PROCURAR IMAGEM NO DIRETÓRIO
def browse_img():
    global pic
    file_path = filedialog.askopenfilename(initialdir=os.getcwd(
    ), title="Select file", filetypes=(("jpg images", ".jpg"), ("all files", "*.*")))   
    t1.set(file_path)
    pic = Image.open(file_path)

# FUNÇÃO PARA REDIMENSIONAR A IMAGEM NO LABEL NOS LABELS DE INPUT E OUTPUT
def reduzir_proporcional():  
    baseheight = 200
    img = pic
    hpercent = (baseheight / float(img.size[1]))
    wsize = int((float(img.size[0]) * float(hpercent)))
    img = img.resize((wsize, baseheight), Image.ANTIALIAS)
    return img   

# ABRIR IMAGEM NO LABEL INPUT
def open_file(): 
    img = reduzir_proporcional()      
    img = ImageTk.PhotoImage(img)    
    lbl_input.configure(image=img)
    lbl_input.image=img   
    lbl_input.pack(fill="both", padx=5, pady=5, anchor="center") 
            
# CONVERTER PARA ESCALA DE CINZA       
def grayscale_mode():    
    global pic
    global img_alterada
    img = reduzir_proporcional().convert('L') 
    img_alterada = img             
    img = ImageTk.PhotoImage(img)           
    lbl_output.configure(image=img)
    lbl_output.image=img      

# ESPELHAR IMAGEM NA HORIZONTAL
def flip_horizontal():
    global pic
    global img_alterada
    img = reduzir_proporcional()
    imgFlipHorizontal = ImageOps.mirror(img)     
    img = ImageTk.PhotoImage(imgFlipHorizontal) 
    img_alterada = imgFlipHorizontal      
    lbl_output.configure(image=img)
    lbl_output.image=img 

# ESPELHAR IMAGEM NA VERTICAL
def flip_vertical():
    global pic
    global img_alterada
    img = reduzir_proporcional()
    imgFlipVertical = ImageOps.flip(img)     
    img = ImageTk.PhotoImage(imgFlipVertical) 
    img_alterada = imgFlipVertical          
    lbl_output.configure(image=img)
    lbl_output.image=img  


#CALCULAR O NEGATIVO DA IMAGEM
def negative_image():   
    global img_alterada    
    img = reduzir_proporcional();
    for i in range(0, img.size[0]-1): 
        for j in range(0, img.size[1]-1):
            pixelColorVals = img.getpixel((i,j)); 
        
            # Invert color
            redPixel    = 255 - pixelColorVals[0]; 
            greenPixel  = 255 - pixelColorVals[1]; 
            bluePixel   = 255 - pixelColorVals[2];         
           
            img.putpixel((i,j),(redPixel, greenPixel, bluePixel));
    
    img_alterada = img   
    img = ImageTk.PhotoImage(img)       
    lbl_output.configure(image=img)
    lbl_output.image=img 


#AJUSTAR O BRILHO DA IMG
def img_brightness():

    if fator.get() == '':
        msg = ('informe um valor para o fator de brilho')         
        messagebox.showinfo("ATENÇÃO!", msg)       

    else:
        global pic
        global img_alterada
        img = reduzir_proporcional()   
        enhancer = ImageEnhance.Brightness(img)
        enhanced_im = enhancer.enhance(float(fator.get()))
        img = ImageTk.PhotoImage(enhanced_im) 
        img_alterada = enhanced_im          
        lbl_output.configure(image=img)
        lbl_output.image=img  


#AJUSTAR O CONTRASTE DA IMG
def img_contraste():

    if fator.get() == '':
        msg = ('informe um valor para o fator de contraste')         
        messagebox.showinfo("ATENÇÃO!", msg)

    else: 
        global pic
        global img_alterada
        img = reduzir_proporcional()   
        enhancer = ImageEnhance.Contrast(img)
        enhanced_im = enhancer.enhance(float(fator.get()))
        img = ImageTk.PhotoImage(enhanced_im) 
        img_alterada = enhanced_im          
        lbl_output.configure(image=img)
        lbl_output.image=img 

#FUNÇÃO PARA SALVAR A IMG
def img_save(): 
    global img_alterada    
    img = img_alterada   
    file_path = filedialog.asksaveasfilename(defaultextension='.png',
    filetypes= [('Image (.png file)','.png')])        
    img.save(file_path)     


'''CRIANDO A TELA PRINCIPAL'''

window = Tk()
window.configure(background='#DBF0F4')
window.title("Mini Editor")
window.resizable(0,0)

window_height = 600
window_width = 650

#FUNÇÃO PARA CENTRALIZAR A TELA
screen_width = window.winfo_screenwidth()
screen_height = window.winfo_screenheight()

x_cordinate = int((screen_width/2) - (window_width/2))
y_cordinate = int((screen_height/2) - (window_height/2))

window.geometry("{}x{}+{}+{}".format(window_width, window_height, x_cordinate, y_cordinate))

t1 = StringVar()


#WRAPPERS
wrapper_img = LabelFrame(window, text="IMAGEM", height=50, font="Poppins 10 bold", fg="white", bg ="#348689")
wrapper_img.pack(fill="both", expand="no", padx=15, pady=15)

wrapper_actions = LabelFrame(window, text="EDIÇÕES", font="Poppins 10 bold", fg="white", bg ="#348689")
wrapper_actions.pack(fill="both", expand="no", padx=15, pady=2)

wrapper_buttons = LabelFrame(wrapper_actions, bg ="#348689")
wrapper_buttons.pack(side=LEFT, fill="both", expand="yes", padx=15, pady=15)

wrapper_factor = LabelFrame(wrapper_actions, width=30, bg ="#348689")
wrapper_factor.pack(side=RIGHT, fill="both", expand="yes", padx=15, pady=15)

wrapper_preview = LabelFrame(window, text ="PREVIEW", height=450, font="Poppins 10 bold", fg="white", bg ="#348689")
wrapper_preview.pack(fill="both", expand="yes", padx=15, pady=15)

wrapper_input = LabelFrame(wrapper_preview, text ="INPUT", font="Poppins 10 bold", fg="white", bg ="#348689")
wrapper_input.pack(side=LEFT, fill="both", expand="yes", padx=15, pady=15, anchor=N)

wrapper_output = LabelFrame(wrapper_preview, text ="OUTPUT", font="Poppins 10 bold", fg="white", bg ="#348689")
wrapper_output.pack(side=RIGHT,fill="both", expand="yes", padx=15, pady=15)

# LABELS
lbl_img = Label(wrapper_img, text="Diretório:", fg="white", font="Calibri 10", bg ="#348689")
lbl_img.pack(side=LEFT, padx=5, pady=5)

lbl_fator = Label(wrapper_factor, text="Fator de\nBrilho e\nContraste", font="Calibri 11 bold", fg="white", bg ="#348689")
lbl_fator.pack(fill='both', padx=5, pady=5)

lbl_input = Label(wrapper_input,bg ="#348689")
lbl_input.pack(fill="both", padx=5, pady=5, anchor="center") 

lbl_output = Label(wrapper_output, bg ="#348689")
lbl_output.pack(fill="both", padx=5, pady=5, anchor="center") 

ent = Entry(wrapper_img, textvariable=t1, width=40)
ent.pack(side=LEFT, padx=5, pady=5)

fator = Entry(wrapper_factor)
fator.pack(fill='x', padx=5, pady=5)

# BUTTONS
btn_browse = Button(wrapper_img, text="PROCURAR IMAGEM", command=browse_img)
btn_browse.config(width=20, fg="white", bg="#404146", font="Poppins 8 bold")
btn_browse.pack(side=LEFT, padx=5, pady=5)

btn_open = Button(wrapper_img, text="ABRIR", command=open_file)
btn_open.config(width=10, fg="white", bg="#404146", font="Poppins 8 bold")
btn_open.pack(side=LEFT, padx=5, pady=5)

btn_grayscale = Button(wrapper_buttons, text="CONVERTER  GRAYSCALE", command=grayscale_mode)
btn_grayscale.config(width=20, height=1, fg="white", bg="#404146", font="Poppins 8 bold")

btn_flipH = Button(wrapper_buttons, text="ESPELHAR  HORIZONTAL", command=flip_horizontal)
btn_flipH.config(width=20, height=1, fg="white", bg="#404146", font="Poppins 8 bold")

btn_flipV = Button(wrapper_buttons, text="ESPELHAR  VERTICAL", command=flip_vertical)
btn_flipV.config(width=20, height=1, fg="white", bg="#404146", font="Poppins 8 bold")

btn_histograma = Button(wrapper_buttons, text="CALCULAR  HISTOGRAMA")
btn_histograma.config(width=20, height=1, fg="white", bg="#404146", font="Poppins 8 bold")

btn_brilho = Button(wrapper_buttons, text="AJUSTAR  BRILHO", command=img_brightness)
btn_brilho.config(width=20, height=1, fg="white", bg="#404146", font="Poppins 8 bold")

btn_contraste = Button(wrapper_buttons, text="AJUSTAR  CONTRASTE", command=img_contraste)
btn_contraste.config(width=20, height=1, fg="white", bg="#404146", font="Poppins 8 bold")

btn_negativo = Button(wrapper_buttons, text="CALCULAR  NEGATIVO", command=negative_image)
btn_negativo.config(width=20, height=1, fg="white", bg="#404146", font="Poppins 8 bold")

btn_equalizar = Button(wrapper_buttons, text="EQUALIZAR  HISTOGRAMA")
btn_equalizar.config(width=20, height=1, fg="white", bg="#404146", font="Poppins 8 bold")

btn_save = Button(wrapper_buttons, text="SALVAR", command=img_save)
btn_save.config(width=20, height=1, fg="white", bg="#404146", font="Poppins 8 bold")


# POSIÇOES DOS BOTÕES NO WRAPPER_ACTIONS (EDIÇÕES)
btn_grayscale.grid(row=1,column=0, padx= 5, pady=4)
btn_histograma.grid(row=1,column=1, padx= 5, pady=4)
btn_brilho.grid(row=1,column=2, padx= 5, pady=5)
btn_flipH.grid(row=2,column=0, padx= 5, pady=4)
btn_negativo.grid(row=2,column=1, padx= 5, pady=4)
btn_contraste.grid(row=2,column=2, padx= 5, pady=4)
btn_flipV.grid(row=3,column=0, padx= 5, pady=4)
btn_equalizar.grid(row=3,column=1, padx= 5, pady=4)
btn_save.grid(row=3,column=2, padx=5 , pady=4)


window.mainloop()


