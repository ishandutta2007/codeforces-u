var x,y:integer;
s:array [0..32] of string;
begin
readln(x,y);
s[0]:='111111101010101111100101001111111';
s[1]:='100000100000000001010110001000001';
s[2]:='101110100110110000011010001011101';
s[3]:='101110101011001001111101001011101';
s[4]:='101110101100011000111100101011101';
s[5]:='100000101010101011010000101000001';
s[6]:='111111101010101010101010101111111';
s[7]:='000000001111101111100111100000000';
s[8]:='100010111100100001011110111111001';
s[9]:='110111001111111100100001000101100';
s[10]:='011100111010000101000111010001010';
s[11]:='011110000110001111110101100000011';
s[12]:='111111111111111000111001001011000';
s[13]:='111000010111010011010011010100100';
s[14]:='101010100010110010110101010000010';
s[15]:='101100000101010001111101000000000';
s[16]:='000010100011001101000111101011010';
s[17]:='101001001111101111000101010001110';
s[18]:='101101111111000100100001110001000';
s[19]:='000010011000100110000011010000010';
s[20]:='001101101001101110010010011011000';
s[21]:='011101011010001000111101010100110';
s[22]:='111010100110011101001101000001110';
s[23]:='110001010010101111000101111111000';
s[24]:='001000111011100001010110111110000';
s[25]:='000000001110010110100010100010110';
s[26]:='111111101000101111000110101011010';
s[27]:='100000100111010101111100100011011';
s[28]:='101110101001010000101000111111000';
s[29]:='101110100011010010010111111011010';
s[30]:='101110100100011011110110101110000';
s[31]:='100000100110011001111100111100000';
s[32]:='111111101101000101001101110010001';
writeln(s[x][y+1]);
end.